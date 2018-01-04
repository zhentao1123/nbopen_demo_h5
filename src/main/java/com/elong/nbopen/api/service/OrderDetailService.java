package com.elong.nbopen.api.service;

import com.elong.nbopen.api.dao.db.IOrderDao;
import com.elong.nbopen.api.model.dao.db.OrderDo;
import com.elong.nbopen.api.model.elong.*;
import com.elong.nbopen.api.model.repository.OrderInfoResult;
import com.elong.nbopen.api.model.viewmodel.order.OrderDetailForPageResult;
import com.elong.nbopen.api.model.viewmodel.order.OrderDetailRequest;
import com.elong.nbopen.api.repository.HotelOrderDetailApi;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderDetailService {

    private Logger logger = Logger.getLogger("OrderDetailService");

    @Autowired
    private HotelOrderDetailApi orderDetailApi;

    @Autowired
    private IOrderDao orderDao;

    /**
     *
     * 获取订单详情
     *
     * @param request
     * @return
     */
    public OrderDetailForPageResult getOrderDetail(OrderDetailRequest request) {
        // 校验该订单是否属于该用户
        OrderDetailForPageResult result = new OrderDetailForPageResult();
        Map<String, Object> params= new HashMap<String, Object>();
        params.put("orderId", request.getOrderId());
        params.put("userId", request.getUsername());
        try {
            if (orderDao.checkOrderWithUser(params) < 0) {
                result.setMessage("用户不存在该订单");
                return result;
            }
        } catch (Exception e) {
            return null;
        }


        OrderIdCondition orderIdCondition = new OrderIdCondition();
        orderIdCondition.setOrderId(request.getOrderId());
        try {
            OrderInfoResult orderInfoResult = orderDetailApi.Invoke(orderIdCondition);
            if (orderInfoResult != null) {
                if (orderInfoResult.getCode().equals("0")) {
                    OrderDetailResult orderDetailResult = orderInfoResult.getResult();
                    result.setSuccess(true);
                    result.setOrderId(orderDetailResult.getOrderId());
                    EnumOrderShowStatus showStatus = EnumOrderShowStatus.fromValue(orderDetailResult.getShowStatus());
                    if (showStatus != null) {
                        result.setShowStatus(showStatus.name());
                    } else {
                        result.setShowStatus("未知状态");
                    }
                    if (orderDetailResult.getCreditCard() != null) {
                        if (orderDetailResult.getCreditCard().isIsPayable()) {
                            result.setLastPayTime(orderDetailResult.getCreditCard().getLatestPayTime());
                        }
                        result.setbPayable(orderDetailResult.getCreditCard().isIsPayable());
                    }
                    result.setbCancel(orderDetailResult.isIsCancelable());
                    result.setHotelId(orderDetailResult.getHotelId());
                    result.setHotelName(orderDetailResult.getHotelName());
                    result.setAddress(orderDetailResult.getOrderHotel().getAddress());
                    result.setHotelPhone(orderDetailResult.getOrderHotel().getPhone());
                    result.setArrivalDate(orderDetailResult.getArrivalDate());
                    result.setDepartureDate(orderDetailResult.getDepartureDate());
                    long numberOfDays=(orderDetailResult.getDepartureDate().getTime()-orderDetailResult.getArrivalDate().getTime())/(1000*3600*24);
                    result.setNumberOfDays(Integer.parseInt(String.valueOf(numberOfDays)));
                    result.setRoomName(orderDetailResult.getRoomTypeName());
                    result.setNumberOfRooms(orderDetailResult.getNumberOfRooms());
                    result.setLastArrivalTime(orderDetailResult.getLatestArrivalTime());
                    String customerNames = "";
                    for (OrderRoom orderRoom : orderDetailResult.getOrderRooms()) {
                        customerNames += orderRoom.getCustomers().get(0).getName();
                    }
                    result.setCustomerNames(customerNames);
                    result.setContactPhone(orderDetailResult.getContact().getMobile());
                    String valueAdds = "";
                    for (String valueAdd : orderDetailResult.getValueAdds()) {
                        valueAdds += valueAdd + "；";
                    }
                    result.setValueAdds(valueAdds);
                    if (orderDetailResult.getInvoice() == null) {
                        if (orderDetailResult.getInvoiceMode() != null && orderDetailResult.getInvoiceMode().name().equals("Hotel")) {
                            result.setInvoiceStatus("发票状态请联系酒店获悉");
                        } else {
                            result.setInvoiceStatus("未开具发票");
                        }
                    } else {
                        if (orderDetailResult.getInvoiceMode().name().equals("Hotel")) {
                            result.setInvoiceStatus("发票状态请联系酒店获悉");
                        } else {
                            String invoiceStatus = "";
                            if (orderDetailResult.getInvoice().getInvoiceType().equals("Paper")) {
                                if (orderDetailResult.getInvoice().isStatus()) {
                                    invoiceStatus += "已开票";
                                } else {
                                    invoiceStatus += "未处理";
                                }
                                if (orderDetailResult.getInvoice().isDeliveryStatus()) {
                                    invoiceStatus += "-已邮寄";
                                } else {
                                    invoiceStatus += "-未邮寄";
                                }
                            } else {
                                switch (orderDetailResult.getInvoice().getProcessType()) {
                                    case 0:
                                        invoiceStatus += "开票";
                                        break;
                                    case 1:
                                        invoiceStatus += "红冲";
                                        break;
                                    case 2:
                                        invoiceStatus += "修改";
                                        break;
                                }
                                switch (orderDetailResult.getInvoice().getProcessStatus()) {
                                    case 0:
                                        invoiceStatus += "-未处理";
                                        break;
                                    case 1:
                                        invoiceStatus += "-处理中";
                                        break;
                                    case 2:
                                        invoiceStatus += "-成功";
                                        break;
                                    case 3:
                                        invoiceStatus += "-失败";
                                        break;
                                }
                            }
                            result.setInvoiceStatus(invoiceStatus);
                            if (StringUtils.isNotBlank(orderDetailResult.getInvoice().getUrlForPDF())) {
                                result.setInvoiceUrl(orderDetailResult.getInvoice().getUrlForPDF());
                            }
                        }
                    }

                    result.setBookingDate(orderDetailResult.getCreationDate());
                    result.setTotalPrice(orderDetailResult.getTotalPrice());
                    result.setPaymentType(orderDetailResult.getPaymentType().name().equals("SelfPay")?"现付":"预付");
                    result.setPenalty(orderDetailResult.getPenaltyToCustomer());

                    // 更新订单数据库
                    try {
                        updateOrder(orderDetailResult);
                    } catch (Exception e) {
                        logger.error(e);
                    }
                } else {
                    logger.error(orderInfoResult.getCode());
                    result.setMessage("获取订单详情失败");
                }
            } else {
                logger.error("请求订单详情超时或网络错误");
            }
        } catch (Exception e) {
            // 捕获异常，以免影响正常的操作
            logger.error(e);
            result.setMessage("获取订单详情异常");
        }
        return result;
    }

    /**
     *
     * 更新订单数据库
     *
     * @param orderDetail
     */
    private void updateOrder(OrderDetailResult orderDetail) {
        // 创建数据库对象
        OrderDo orderDo = new OrderDo();
        orderDo.setOrderId(orderDetail.getOrderId());
        orderDo.setStatus(orderDetail.getStatus());
        orderDo.setShowStatus(orderDetail.getShowStatus());
        orderDo.setArrivalDate(orderDetail.getArrivalDate());
        orderDo.setDepartureDate(orderDetail.getDepartureDate());
        orderDo.setTotalPrice(orderDetail.getTotalPrice().doubleValue());
        orderDo.setRoomNum(orderDetail.getNumberOfRooms());
        if (orderDetail.getCreditCard() != null) {
            EnumCreditCardStatus creditCardStatus = orderDetail.getCreditCard().getStatus();
            switch (creditCardStatus.value()){
                case "UnProcess":
                    orderDo.setPayStatus(-1);
                    break;
                case "Succeed":
                    orderDo.setPayStatus(3);
                    break;
                case "Processing":
                    orderDo.setPayStatus(2);
                    break;
                case "Fail":
                    orderDo.setPayStatus(4);
                    break;
                default:
                    orderDo.setPayStatus(0);
                    break;
            }
            orderDo.setNeedPay(orderDetail.getCreditCard().isIsPayable());
            orderDo.setCancelTime(orderDetail.getCancelTime());
            orderDo.setContactPhone(orderDetail.getContact().getMobile());
            orderDo.setTotalPrice(orderDetail.getTotalPrice().doubleValue());
            orderDo.setRatePlanId(orderDetail.getRatePlanId());
            orderDo.setRoomTypeId(orderDetail.getRoomTypeId());
            orderDo.setHotelId(orderDetail.getHotelId());
            orderDo.setShowStatus(orderDetail.getShowStatus());
            orderDo.setHotelName(orderDetail.getHotelName());
            orderDo.setRoomName(orderDetail.getRoomTypeName());
            orderDo.setRatePlanName(orderDetail.getRatePlanName());
        }

        orderDao.updateOrder(orderDo);
    }
}
