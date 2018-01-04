package com.elong.nbopen.api.controller;

import com.elong.nbopen.api.model.viewmodel.order.ListRequest;
import com.elong.nbopen.api.model.viewmodel.order.ListResult;
import com.elong.nbopen.api.service.OrderListService;
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
public class OrderListController {
    private Logger logger = Logger.getLogger("OrderDetailController");

    @Autowired
    private OrderListService orderListService;

    /**
     *
     * 订单列表
     *
     * @param request
     * @param requestbody
     * @return
     */
    @RequestMapping(value = "/getOrderList", method= RequestMethod.POST)
    @ResponseBody
    public ListResult getOrderList(HttpServletRequest request, @RequestBody ListRequest requestbody) {
        ListResult result = null;

        // 参数校验
        String errorMsg = requestbody.validate();
        if (StringUtils.isNotBlank(errorMsg)) {
            result = new ListResult();
            result.setMessage(errorMsg);
            return result;
        }

        result = orderListService.getOrderList(requestbody);
        return result;
    }
}
