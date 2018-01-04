package com.elong.nbopen.api.controller;

import com.elong.nbopen.api.model.viewmodel.order.CreateRequest;
import com.elong.nbopen.api.model.viewmodel.order.CreateResult;
import com.elong.nbopen.api.service.OrderCreateService;
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
public class OrderCreateController {

    private Logger logger = Logger.getLogger("OrderCreateController");

    @Autowired
    private OrderCreateService orderCreateService;

    /**
     *
     * 创建订单
     *
     * @param request
     * @param requestbody
     * @return
     */
    @RequestMapping(value = "/createOrder", method= RequestMethod.POST)
    @ResponseBody
    public CreateResult createOrder(HttpServletRequest request, @RequestBody CreateRequest requestbody) {
        CreateResult result = null;

        // 参数校验
        String errorMsg = requestbody.validate();
        if (StringUtils.isNotBlank(errorMsg)) {
            result = new CreateResult();
            result.setErrorMessage(errorMsg);
            return result;
        }

        result = orderCreateService.createOrder(requestbody);
        return result;
    }
}
