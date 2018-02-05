package com.elong.nbopen.api.service;

import com.elong.nbopen.api.dao.db.IOrderDao;
import com.elong.nbopen.api.model.dao.db.OrderDo;
import com.elong.nbopen.api.model.elong.EnumOrderShowStatus;
import com.elong.nbopen.api.model.viewmodel.order.ListRequest;
import com.elong.nbopen.api.model.viewmodel.order.ListResult;
import com.elong.nbopen.api.model.viewmodel.order.SimpleOrderResult;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderListService {

    private Logger logger = Logger.getLogger("OrderListService");

    @Autowired
    private IOrderDao orderDao;

    // 默认每次查询5条订单信息
    private static final int pageSize = 5;

    public ListResult getOrderList(ListRequest request) {
        ListResult result = new ListResult();
        List<SimpleOrderResult> listResult = new ArrayList<SimpleOrderResult>();

        try {
            int from = (request.getPage() - 1) * pageSize;
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("userId", request.getUsername());
            params.put("begin", from);
            params.put("size", pageSize);
            List<OrderDo> queryResult = orderDao.queryOrdersByUserId(params);
            if (queryResult != null && queryResult.size() > 0) {
                for (OrderDo orderDo : queryResult) {
                    SimpleOrderResult simpleOrderResult = new SimpleOrderResult();
                    simpleOrderResult.setOrderId(orderDo.getOrderId());
                    EnumOrderShowStatus showStatus = EnumOrderShowStatus.fromValue(orderDo.getShowStatus());
                    if (showStatus != null) {
                        simpleOrderResult.setShowStatus(showStatus.name());
                    } else {
                        simpleOrderResult.setShowStatus("未知状态");
                    }
                    simpleOrderResult.setHotelId(orderDo.getHotelId());
                    simpleOrderResult.setHotelName(orderDo.getHotelName());
                    simpleOrderResult.setRoomName(orderDo.getRoomName());
                    simpleOrderResult.setPaymentType(orderDo.getPaymentType() == 0? "现付": "预付");
                    simpleOrderResult.setTotalPrice(new BigDecimal(orderDo.getTotalPrice()));
                    simpleOrderResult.setCurrencyCode(StringUtils.isBlank(orderDo.getCurrencyCode())? "RMB": orderDo.getCurrencyCode());
                    simpleOrderResult.setPayAmount(new BigDecimal(orderDo.getPayAmount()));
                    simpleOrderResult.setContactName(orderDo.getContactName());

                    // 入离日期描述
                    String[] weekOfDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(orderDo.getArrivalDate());
                    int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                    if (w < 0){
                        w = 0;
                    }
                    String dateDescription = (calendar.get(Calendar.MONTH)+1)+"月"+(calendar.get(Calendar.DAY_OF_MONTH))+"日（"+weekOfDays[w]+"） - ";
                    calendar.setTime(orderDo.getDepartureDate());
                    w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                    if (w < 0){
                        w = 0;
                    }
                    dateDescription += (calendar.get(Calendar.MONTH)+1)+"月"+(calendar.get(Calendar.DAY_OF_MONTH))+"日（"+weekOfDays[w]+"）";
                    simpleOrderResult.setDateDescription(dateDescription);

                    long numberOfDays=(orderDo.getDepartureDate().getTime()-orderDo.getArrivalDate().getTime())/(1000*3600*24);
                    simpleOrderResult.setNumberOfDays(Integer.parseInt(String.valueOf(numberOfDays)));
                    simpleOrderResult.setNumberOfRooms(orderDo.getRoomNum());

                    simpleOrderResult.setbPayable(orderDo.getNeedPay());
                    // 可以支付的情况下也可以取消
                    if (orderDo.getNeedPay()) {
                        simpleOrderResult.setbCancel(true);
                    } else if (orderDo.getStatus().equals("D") || orderDo.getStatus().equals("E")) {
                        // 取消成功或者取消中时不可取消
                        simpleOrderResult.setbCancel(false);
                    } else {
                        simpleOrderResult.setbCancel(orderDo.getCancelTime().getTime() > new Date().getTime());
                    }

                    // 如果处于E状态48小时，那么可以认为取消成功
                    if (orderDo.getCancelRecieveTime().getTime() != -28800000L
                            &&new Date().getTime() - orderDo.getCancelRecieveTime().getTime() > 48 * 3600000
                            && orderDo.getShowStatus() != 256L) {
                        try {
                            OrderDo newOrderDo = new OrderDo();
                            newOrderDo.setOrderId(orderDo.getOrderId());
                            newOrderDo.setShowStatus(256L);
                            orderDao.updateOrder(newOrderDo);
                        } catch (Exception e) {
                            logger.error(e);
                        }
                        simpleOrderResult.setbCancel(false);
                        simpleOrderResult.setShowStatus("已经取消");
                    }

                    listResult.add(simpleOrderResult);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        result.setOrders(listResult);
        return result;
    }
}
