package com.elong.nbopen.api.service;

import com.elong.nbopen.api.dao.db.IOrderDao;
import com.elong.nbopen.api.model.dao.db.OrderDo;
import com.elong.nbopen.api.model.elong.*;
import com.elong.nbopen.api.model.repository.CreditValidateResult;
import com.elong.nbopen.api.model.repository.OrderPayResult;
import com.elong.nbopen.api.model.viewmodel.order.PayRequest;
import com.elong.nbopen.api.model.viewmodel.order.PayResult;
import com.elong.nbopen.api.model.viewmodel.order.SimpleProductResult;
import com.elong.nbopen.api.repository.CreditCardValidateApi;
import com.elong.nbopen.api.repository.HotelOrderPayApi;
import com.elong.nbopen.api.util.CipherUtil;
import com.elong.nbopen.api.util.CommonsUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderPayService {
    private static final long MILLISECONDOFDAY = 1000 * 60 * 60 * 24;

    private Logger logger = Logger.getLogger("OrderPayService");

    // AppKey后八位
    private static String subAppKey = CommonsUtil.CONFIG_PROVIDAR.getProperty("appkey").substring(CommonsUtil.CONFIG_PROVIDAR.getProperty("appkey").length()-8);

    @Autowired
    private HotelOrderPayApi orderPayApi;

    @Autowired
    private CreditCardValidateApi creditCardValidateApi;

    @Autowired
    private IOrderDao orderDao;

    /**
     *
     * 支付订单
     *
     * @param request
     * @return
     */
    public PayResult payForOrder(PayRequest request) {
        SubmitOrderPaymentInfoRequest condition = new SubmitOrderPaymentInfoRequest();
        // 此处默认使用信用卡进行支付或者担保，开通公司担保的代理请自行修改相关代码
        condition.setIsGuaranteeOrCharged(false);
        condition.setOrderId(request.getOrderId());

        CreditCard creditCard = request.getCreditCard();
        // 信用卡加密
        Long time = new Date().getTime()/1000;
        try {
            creditCard.setNumber(CipherUtil.desEncrypt(time.toString()+"#"+creditCard.getNumber(), subAppKey));
        } catch (Exception e) {
            logger.error("信用卡加密失败：" + e);
        }
        condition.setCreditCard(creditCard);
        condition.setAmount(request.getPayAmount());


        OrderPayResult payResult = null;
        try {
            payResult = orderPayApi.Invoke(condition);
        } catch (Exception e) {
            logger.error("支付异常：" + e);
        }

        PayResult result = new PayResult();
        if (payResult != null && payResult.getCode() != null && payResult.getCode().equals("0") && payResult.getResult() != null) {
            result.setSuccess(payResult.getResult().isIsSuccess());
            result.setMessage(payResult.getResult().getNotes());
            // 如果支付成功，更新本地数据库的支付状态
            if (payResult.getResult().isIsSuccess()) {
                OrderDo orderDo = new OrderDo();
                orderDo.setOrderId(request.getOrderId());
                // 2-担保或支付中
                orderDo.setPayStatus(2);
                // 此时不需要支付
                orderDo.setNeedPay(false);
                orderDao.updateOrder(orderDo);
            }
        } else if (payResult != null && payResult.getCode() != null && !payResult.getCode().equals("0")) {
            result.setMessage(payResult.getCode().split("\\|")[1]);
        } else {
            result.setMessage("支付发生异常");
        }
        return result;
    }

    /**
     * 校验信用卡
     *
     * @param creditCardNum
     * @return
     */
    public ValidateCreditCardResult validateCreditCard(String creditCardNum) {
        ValidateCreditCardResult result = new ValidateCreditCardResult();
        ValidateCreditCardCondition condition = new ValidateCreditCardCondition();
        Long time = new Date().getTime()/1000;
        try {
            creditCardNum = CipherUtil.desEncrypt(time.toString()+"#"+creditCardNum, subAppKey);
        } catch (Exception e) {
            // 加密失败就不校验了 ，为了安全
            logger.error("信用卡加密失败：" + e);
            return result;
        }
        condition.setCreditCardNo(creditCardNum);
        CreditValidateResult validateResult = null;
        try {
            validateResult = creditCardValidateApi.Invoke(condition);
        } catch (Exception e) {
            logger.error("信用卡校验失败：" + e);
        }

        if (validateResult != null && validateResult.getCode().equals("0") && validateResult.getResult() != null) {
            result = validateResult.getResult();
        }
        return result;
    }

    /**
     *
     * 获取订单的简易产品信息
     *
     * @param orderId
     * @return
     */
    public SimpleProductResult getSimpleProduct(long orderId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderId", orderId);
        OrderDo orderDo = null;
        try {
            orderDo = orderDao.queryOrderByOrderId(params);
        } catch (Exception e){

        }
        SimpleProductResult result = null;
        if (orderDo != null) {
            result = new SimpleProductResult();
            result.setHotelName(orderDo.getHotelName());
            result.setRoomName(orderDo.getRoomName());
            result.setRatePlanName(orderDo.getRatePlanName());
            result.setRoomNum(orderDo.getRoomNum());
            result.setTotalRate(new BigDecimal(orderDo.getTotalPrice()));
            result.setPayAmount(new BigDecimal(orderDo.getPayAmount()));
            result.setPaymentType(orderDo.getPaymentType() == 0? "SelfPay": "Prepay");

            // 在店天数
            result.setNumberOrDays ((orderDo.getDepartureDate().getTime() - orderDo.getArrivalDate().getTime()) / MILLISECONDOFDAY);

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
            result.setDateDescription(dateDescription);
        }
        return result;
    }
}
