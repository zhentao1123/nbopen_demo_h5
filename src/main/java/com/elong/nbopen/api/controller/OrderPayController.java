package com.elong.nbopen.api.controller;

import com.elong.nbopen.api.model.elong.ValidateCreditCardResult;
import com.elong.nbopen.api.model.viewmodel.order.CreditValidateRequest;
import com.elong.nbopen.api.model.viewmodel.order.PayRequest;
import com.elong.nbopen.api.model.viewmodel.order.PayResult;
import com.elong.nbopen.api.model.viewmodel.order.SimpleProductResult;
import com.elong.nbopen.api.service.OrderPayService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/api/order")
public class OrderPayController {

    private Logger logger = Logger.getLogger("OrderPayController");

    @Autowired
    private OrderPayService orderPayService;

    /**
     *
     * 支付订单
     *
     * @param request
     * @param requestbody
     * @return
     */
    @RequestMapping(value = "/payOrder", method= RequestMethod.POST)
    @ResponseBody
    public PayResult payOrder(HttpServletRequest request, @RequestBody PayRequest requestbody) {
        PayResult result = null;

        // 参数校验
        String errorMsg = requestbody.validate();
        if (StringUtils.isNotBlank(errorMsg)) {
            result = new PayResult();
            result.setMessage(errorMsg);
            return result;
        }

        result = orderPayService.payForOrder(requestbody);
        return result;
    }

    /**
     *
     * 校验信用卡
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/validateCreditCard", method= RequestMethod.POST)
    @ResponseBody
    public ValidateCreditCardResult validateCreditCard(HttpServletRequest request, @RequestBody CreditValidateRequest requestBody) {
        if (requestBody == null || StringUtils.isBlank(requestBody.getCreditCardNum())) {
            return new ValidateCreditCardResult();
        }
        return orderPayService.validateCreditCard(requestBody.getCreditCardNum());
    }

    /**
     *
     * 获取订单的基础产品信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getSimpleProduct", method= RequestMethod.GET)
    @ResponseBody
    public SimpleProductResult getSimpleProduct(HttpServletRequest request, @RequestParam("orderId") long orderId) {
        return orderPayService.getSimpleProduct(orderId);
    }
}
