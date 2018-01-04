package com.elong.nbopen.api.service;

import com.elong.nbopen.api.dao.db.IOrderDao;
import com.elong.nbopen.api.model.dao.db.OrderDo;
import com.elong.nbopen.api.model.elong.*;
import com.elong.nbopen.api.model.repository.OrderCreateResult;
import com.elong.nbopen.api.model.repository.OrderInfoResult;
import com.elong.nbopen.api.model.viewmodel.order.CreateRequest;
import com.elong.nbopen.api.model.viewmodel.order.CreateResult;
import com.elong.nbopen.api.repository.HotelOrderCreateApi;
import com.elong.nbopen.api.repository.HotelOrderDetailApi;
import com.elong.nbopen.api.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderCreateService {

    private Logger logger = Logger.getLogger("OrderCreateService");

    @Autowired
    private HotelOrderCreateApi hotelOrderCreateApi;

    @Autowired
    private HotelOrderDetailApi hotelOrderDetailApi;

    @Autowired
    private IOrderDao orderDao;

    /**
     * 创建订单
     *
     * @param request
     * @return
     */
    public CreateResult createOrder(CreateRequest request) {
        CreateOrderCondition condition = new CreateOrderCondition();

        // 生成第三方订单号，订单号最多30位
        String affiliateConfirmationId = request.getUserName() + ((Long)System.currentTimeMillis()).toString();
        condition.setAffiliateConfirmationId(affiliateConfirmationId);

        condition.setHotelId(request.getHotelId());
        condition.setRoomTypeId(request.getRoomTypeId());
        condition.setRatePlanId(request.getRatePlanId());

        condition.setArrivalDate(request.getArrivalDate());
        condition.setDepartureDate(request.getDepartureDate());
        condition.setEarliestArrivalTime(request.getEarliestArrivalTime());
        condition.setLatestArrivalTime(request.getLatestArrivalTime());

        condition.setPaymentType(EnumPaymentType.valueOf(request.getPaymentType()));
        condition.setCustomerType(EnumGuestTypeCode.valueOf(request.getCustomerType()));

        condition.setNumberOfRooms(request.getNumberOfRooms());
        condition.setNumberOfCustomers(request.getNumberOfRooms());

        condition.setCurrencyCode(EnumCurrencyCode.valueOf(request.getCurrencyCode()));
        condition.setTotalPrice(request.getTotalPrice());
        condition.setCustomerIPAddress("127.0.0.1");

        // 发票信息
        if (request.getNeedInvoice()) {
            condition.setIsNeedInvoice(true);
            condition.setInvoice(request.getInvoice());
        } else {
            condition.setIsNeedInvoice(request.getNeedInvoice());
            condition.setInvoice(request.getInvoice());
        }


        // 仅创建订单
        condition.setIsCreateOrderOnly(true);

        // 确认类型
        condition.setConfirmationType(EnumConfirmationType.valueOf(CommonService.CONFIRMATION_TYPE));

        List<CreateOrderRoom> orderRooms = new ArrayList<CreateOrderRoom>();
        for (int i = 0; i < request.getNumberOfRooms(); i++) {
            CreateOrderRoom orderRoom = new CreateOrderRoom();
            List<Customer> customers = new ArrayList<Customer>();
            CustomerForOrder customer = new CustomerForOrder();
            customer.setName(request.getCustomers().get(i));
            // 入住人姓名和国籍建议填写，此处默认也可，但是酒店有可能拒绝此订单
            customer.setGender(EnumGender.Unknown);
            customer.setNationality("未填写");
            customers.add(customer);
            orderRoom.setCustomers(customers);
            orderRooms.add(orderRoom);
        }
        condition.setOrderRooms(orderRooms);

        Contact contact = new Contact();
        contact.setName(request.getContactName());
        contact.setMobile(request.getContactPhone());
        condition.setContact(contact);

        CreateResult result = new CreateResult();
        try {
            OrderCreateResult createResult = hotelOrderCreateApi.Invoke(condition);
            if (createResult != null && createResult.getCode().equals("0")) {
                result.setOrderId(createResult.getResult().getOrderId());
                result.setSuccess(true);

                // 创建数据库插入对象
                OrderDo orderDo = new OrderDo();
                orderDo.setOrderId(createResult.getResult().getOrderId());
                orderDo.setAffiliateConfirmationId(affiliateConfirmationId);
                orderDo.setUserId(request.getUserName());
                orderDo.setBookingTime(new Date());
                orderDo.setHotelId(request.getHotelId());
                orderDo.setRoomTypeId(request.getRoomTypeId());
                orderDo.setRatePlanId(request.getRatePlanId());
                if (request.getPaymentType().equals("SelfPay")) {
                    orderDo.setPaymentType(0);
                    if (createResult.getResult().getGuaranteeAmount() != null) {
                        orderDo.setPayAmount(createResult.getResult().getGuaranteeAmount().doubleValue());
                    } else {
                        orderDo.setPayAmount(0.0);
                    }
                } else {
                    orderDo.setPaymentType(1);
                    orderDo.setPayAmount(request.getTotalPrice().doubleValue());
                }
                orderDo.setArrivalDate(request.getArrivalDate());
                orderDo.setDepartureDate(request.getDepartureDate());
                orderDo.setTotalPrice(request.getTotalPrice().doubleValue());
                orderDo.setContactName(request.getContactName());
                orderDo.setContactPhone(request.getContactPhone());
                orderDo.setRoomNum(request.getNumberOfRooms());

                /************************* 需要请求订单详情才能得知的信息 ************************/
                // 新创建的订单状态默认为N-新单
                orderDo.setStatus("N");
                // 新创建订单展示状态设置为0，此非正常的展示状态，仅用于数据库对象的初始化
                orderDo.setShowStatus(0l);

                // 以下用于展示的产品相关信息初始化为--
                orderDo.setHotelName("--");
                orderDo.setRoomName("--");
                orderDo.setRatePlanName("--");
                orderDo.setHotelPhone("--");

                // 将最晚取消时间和艺龙收到取消请求的时间初始化为1970-01-01
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+08:00");
                orderDo.setCancelTime(dateFormat.parse("1970-01-01T00:00:00+08:00"));
                orderDo.setCancelRecieveTime(dateFormat.parse("1970-01-01T00:00:00+08:00"));

                // 将支付信息初始化为不需要支付和未支付
                orderDo.setNeedPay(false);
                orderDo.setPayStatus(0);
                /************************* 需要请求订单详情才能得知的信息 ************************/

                // 获取订单详情
                OrderIdCondition orderIdCondition = new OrderIdCondition();
                orderIdCondition.setOrderId(createResult.getResult().getOrderId());
                try {
                    OrderInfoResult orderInfoResult = hotelOrderDetailApi.Invoke(orderIdCondition);
                    if (orderInfoResult != null) {
                        if (orderInfoResult.getCode().equals("0")) {
                            OrderDetailResult orderDetail = orderInfoResult.getResult();
                            orderDo.setStatus(orderDetail.getStatus());
                            orderDo.setShowStatus(orderDetail.getShowStatus());
                            orderDo.setHotelName(orderDetail.getHotelName());
                            orderDo.setRoomName(orderDetail.getRoomTypeName());
                            orderDo.setRatePlanName(orderDetail.getRatePlanName());
                            orderDo.setCancelTime(orderDetail.getCancelTime());
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

                if (request.getPaymentType().equals("Prepay")) {
                    result.setNeedPay(true);
                    orderDo.setNeedPay(true);
                } else if (createResult.getResult().getGuaranteeAmount().doubleValue() > 0){
                    result.setNeedPay(true);
                } else {
                    result.setNeedPay(false);
                }

                try {
                    if (orderDao.addOrder(orderDo) < 1) {
                        logger.error("【数据库操作失败，订单信息：】" + JsonUtil.entity2Json(orderDo));
                    }
                } catch (Exception e) {
                    // 捕获异常，以免影响正常的返回操作
                    logger.error("【数据库操作异常，订单信息：】" + JsonUtil.entity2Json(orderDo));
                }
            } else {
                result.setErrorMessage(createResult.getCode());
            }
        } catch (Exception e) {
            result.setErrorMessage(e.getMessage());
            logger.error(e);
        }

        return result;
    }
}
