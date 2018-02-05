package com.elong.nbopen.sync.task;

import com.elong.nbopen.api.dao.db.IOrderDao;
import com.elong.nbopen.api.model.dao.db.OrderDo;
import com.elong.nbopen.api.model.elong.*;
import com.elong.nbopen.api.model.repository.OrderIncrResult;
import com.elong.nbopen.api.model.repository.OrderInfoResult;
import com.elong.nbopen.api.repository.HotelIncrOrderApi;
import com.elong.nbopen.api.repository.HotelOrderDetailApi;
import com.elong.nbopen.api.service.CommonService;
import com.elong.nbopen.sync.dao.db.IIncrOrderManagerDao;
import com.elong.nbopen.sync.model.dao.db.IncrOrderManagerDo;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class IncrOrderTask extends Thread implements InitializingBean {

    private Logger logger = Logger.getLogger("IncrOrderTask");

    @Autowired
    private IIncrOrderManagerDao incrOrderManagerDao;

    @Autowired
    private IOrderDao orderDao;

    @Autowired
    private HotelIncrOrderApi incrOrderApi;

    @Autowired
    private HotelOrderDetailApi hotelOrderDetailApi;

    // 更新周期的多少倍没有更新心跳时间当做更新线程死亡
    private int beatTimesOfDie = 4;

    // 不执行任务时睡眠时间
    private long noTaskSleep = CommonService.INCR_ORDER_FREQUENCY * beatTimesOfDie;

    public void run() {
        while (true) {
            readTask();
        }
    }

    @Transactional
    public void readTask() {
        try {
            IncrOrderManagerDo managerDo = incrOrderManagerDao.getManagerInfo();

            // 首先判断是否要抢占更新任务资源
            // mac地址为空 或者 心跳时间为1970-01-01 或者 心跳时间超过设置的更新频率间隔的四倍并且在5分钟以上 则认为原有的更新任务死掉了 需要抢占
            if (StringUtils.isBlank(managerDo.getMacAddr())
                    || managerDo.getBeatTime().getTime() == -28800000
                    || ((new Date().getTime() - managerDo.getBeatTime().getTime()) / CommonService.INCR_ORDER_FREQUENCY > 4
                    && (new Date().getTime() - managerDo.getBeatTime().getTime()) / 60000 > 5)) {
                // 此时需要抢占
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("newMacAddr", CommonService.MAC_ADDR);
                params.put("beatTime", new Date());
                params.put("oldMacAddr", managerDo.getMacAddr());
                incrOrderManagerDao.updateMacAddr(params);
            } else {
                // 此时不需要抢占 如果mac地址是本机地址，那么执行更新任务，否则睡眠等待
                if (managerDo.getMacAddr().equals(CommonService.MAC_ADDR)) {
                    updateOrder(managerDo);
                    sleep(CommonService.INCR_ORDER_FREQUENCY);
                } else {
                    sleep(noTaskSleep);
                }
            }
        } catch (Exception e) {
            // 吃掉异常，避免影响正常业务
            logger.error(e);
        }
    }

    /**
     * 更新订单信息
     *
     * @param managerDo
     */
    private void updateOrder(IncrOrderManagerDo managerDo) {

        IncrCondition condition = new IncrCondition();
        condition.setLastId(managerDo.getLastId());

        OrderIncrResult incrResult = null;
        try {
            incrResult = incrOrderApi.Invoke(condition);
        } catch (Exception e) {
            logger.error(e);
        }

        if (incrResult != null && incrResult.getCode().equals("0")
                && incrResult.getResult() != null && incrResult.getResult().getOrders() != null
                && incrResult.getResult().getOrders().size() > 0) {
            List<Order> orderList = incrResult.getResult().getOrders();
            for (int i = 0; i< orderList.size(); i++) {
                Order order = orderList.get(i);

                // 创建数据库对象
                OrderDo orderDo = new OrderDo();
                orderDo.setOrderId(order.getOrderId());
                orderDo.setStatus(order.getStatus());
                orderDo.setArrivalDate(order.getArrivalDate());
                orderDo.setDepartureDate(order.getDepartureDate());
                orderDo.setAffiliateConfirmationId(order.getAffiliateConfirmationId());
                orderDo.setTotalPrice(order.getTotalPrice().doubleValue());
                orderDo.setRoomNum(order.getNumberOfRooms());
                orderDo.setPayStatus(order.getPayStatus());

                // 获取订单详情
                OrderIdCondition orderIdCondition = new OrderIdCondition();
                orderIdCondition.setOrderId(order.getOrderId());
                try {
                    OrderInfoResult orderInfoResult = hotelOrderDetailApi.Invoke(orderIdCondition);
                    if (orderInfoResult != null) {
                        if (orderInfoResult.getCode().equals("0")) {
                            OrderDetailResult orderDetail = orderInfoResult.getResult();
                            orderDo.setStatus(orderDetail.getStatus());
                            orderDo.setShowStatus(orderDetail.getShowStatus());
                            orderDo.setHotelId(orderDetail.getHotelId());
                            orderDo.setRoomTypeId(orderDetail.getRoomTypeId());
                            orderDo.setRatePlanId(orderDetail.getRatePlanId());
                            orderDo.setHotelName(orderDetail.getHotelName());
                            orderDo.setRoomName(orderDetail.getRoomTypeName());
                            orderDo.setRatePlanName(orderDetail.getRatePlanName());
                            orderDo.setHotelPhone(orderDetail.getOrderHotel().getPhone());
                            orderDo.setBookingTime(orderDetail.getCreationDate());
                            orderDo.setCancelTime(orderDetail.getCancelTime());
                            orderDo.setCurrencyCode(orderDetail.getCurrencyCode().name());
                            orderDo.setBookingTime(new Date());
                            if (orderDetail.getPaymentType().equals(EnumPaymentType.SelfPay)) {
                                orderDo.setPaymentType(0);
                            } else {
                                orderDo.setPaymentType(1);
                            }
                            orderDo.setArrivalDate(orderDetail.getArrivalDate());
                            orderDo.setDepartureDate(orderDetail.getDepartureDate());
                            orderDo.setTotalPrice(orderDetail.getTotalPrice().doubleValue());
                            orderDo.setContactName(orderDetail.getContact().getName());
                            orderDo.setContactPhone(orderDetail.getContact().getMobile());
                            orderDo.setRoomNum(orderDetail.getNumberOfRooms());

                            // 更新订单是否可支付状态,支付成功后不需要再支付
                            if (order.getPayStatus() == 3) {
                                orderDo.setNeedPay(false);
                            } else if (orderDetail.getCreditCard() != null) {
                                orderDo.setNeedPay(orderDetail.getCreditCard().isIsPayable());
                            } else {
                                orderDo.setNeedPay(false);
                            }

                            // 关联订单是指以为原有订单不能描述用户的实际入住信息，由艺龙客服进行拆单产生的
                            // 即原有的一个订单拆成了两个订单，可以通过hotel.order.related接口获取具体的订单信息
                            // 此处不做处理，仅将关联订单插入数据库中，代理如果想做关联订单逻辑可以自行设计
                            if (orderDao.updateOrder(orderDo) == 0) {
                                // 关联订单不存在第三方订单号
                                orderDo.setAffiliateConfirmationId("");
                                // 关联订单的用户名存在于其父订单
                                orderDo.setUserId("");
                                // 收到取消请求的时间
                                orderDo.setCancelRecieveTime(new SimpleDateFormat("yyyy-MM-dd").parse("1970-01-01"));
                                orderDao.addOrder(orderDo);
                            }

                            // 请求一次订单详情后睡眠100毫秒以防访问频繁
                            Thread.sleep(300);
                        } else {
                            logger.error(orderInfoResult.getCode());
                        }
                    } else {
                        logger.error("请求订单详情超时或网络错误");
                    }
                } catch (Exception e) {
                    // 捕获异常，以免影响正常的数据库操作
                    logger.error(e);
                }

                // 需要保证更新线程遇到大量更新时不会因为处理时间过长而导致其他服务器线程抢占资源
                // 由于设置了最低5分钟没有更新才会被其他服务器线程抢占，所以此处检测到超过4分钟的时候就先更新下心跳时间
                long timestamp = new Date().getTime();
                if ((timestamp - managerDo.getBeatTime().getTime()) / 60000 >= 4) {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("macAddr", managerDo.getMacAddr());
                    params.put("beatTime", new Date());
                    params.put("lastId", order.getLastId());
                    incrOrderManagerDao.updateBeatTime(params);
                }
            }
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("macAddr", managerDo.getMacAddr());
            params.put("beatTime", new Date());
            params.put("lastId", orderList.get(orderList.size() - 1).getLastId());
            incrOrderManagerDao.updateBeatTime(params);
        }


    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.start();
    }
}
