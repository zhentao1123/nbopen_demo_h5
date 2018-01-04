package com.elong.nbopen.api.controller;

import com.elong.nbopen.api.model.viewmodel.order.CancelRequest;
import com.elong.nbopen.api.model.viewmodel.order.CancelResult;
import com.elong.nbopen.api.service.OrderCancelService;
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
public class OrderCancelController {

    private Logger logger = Logger.getLogger("OrderCancelController");

    @Autowired
    private OrderCancelService orderCancelService;

    /**
     *
     * 取消订单
     *
     * @param request
     * @param requestbody
     * @return
     */
    @RequestMapping(value = "/cancelOrder", method= RequestMethod.POST)
    @ResponseBody
    public CancelResult cancelOrder(HttpServletRequest request, @RequestBody CancelRequest requestbody) {
        CancelResult result = null;

        // 参数校验
        String errorMsg = requestbody.validate();
        if (StringUtils.isNotBlank(errorMsg)) {
            result = new CancelResult();
            result.setReason(errorMsg);
            return result;
        }

        result = orderCancelService.cancelOrder(requestbody);
        return result;
    }
}
