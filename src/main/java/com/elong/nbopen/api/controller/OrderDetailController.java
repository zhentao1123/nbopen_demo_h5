package com.elong.nbopen.api.controller;

import com.elong.nbopen.api.model.viewmodel.order.OrderDetailForPageResult;
import com.elong.nbopen.api.model.viewmodel.order.OrderDetailRequest;
import com.elong.nbopen.api.service.OrderDetailService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/api/order")
public class OrderDetailController {
    private Logger logger = Logger.getLogger("OrderDetailController");

    @Autowired
    private OrderDetailService orderDetailService;

    /**
     *
     * 订单详情
     *
     * @param request
     * @param requestbody
     * @return
     */
    @RequestMapping(value = "/getOrderDetail", method= RequestMethod.POST)
    @ResponseBody
    public OrderDetailForPageResult getOrderDetail(HttpServletRequest request, @RequestBody OrderDetailRequest requestbody) {
        OrderDetailForPageResult result = null;

        // 参数校验
        String errorMsg = requestbody.validate();
        if (StringUtils.isNotBlank(errorMsg)) {
            result = new OrderDetailForPageResult();
            result.setMessage(errorMsg);
            return result;
        }

        result = orderDetailService.getOrderDetail(requestbody);
        return result;
    }
}
