package com.elong.nbopen.api.controller;

import com.elong.nbopen.api.model.viewmodel.hotel.DetailForOrderWrite;
import com.elong.nbopen.api.model.viewmodel.order.ValidateRequest;
import com.elong.nbopen.api.model.viewmodel.order.ValidateResult;
import com.elong.nbopen.api.model.viewmodel.order.WriteRequest;
import com.elong.nbopen.api.service.HotelDetailService;
import com.elong.nbopen.api.service.OrderBeforeService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 2017/12/21.
 */
@Controller
@RequestMapping(value = "/api/order")
public class OrderWriteController {

    private Logger logger = Logger.getLogger("OrderWriteController");

    @Autowired
    private HotelDetailService hotelDetailService;

    @Autowired
    private OrderBeforeService orderBeforeService;

    /**
     *
     * 获取产品基础信息
     *
     * @param request
     * @param requestbody
     * @return
     */
    @RequestMapping(value = "/getDetailForWrite", method= RequestMethod.POST)
    @ResponseBody
    public DetailForOrderWrite getDetailForWrite(HttpServletRequest request, @RequestBody WriteRequest requestbody) {
        DetailForOrderWrite result = null;

        // 参数校验
        if (StringUtils.isNotBlank(requestbody.validate())) {
            return null;
        }

        result = hotelDetailService.getDetailForOrderWrite(requestbody);
        return result;
    }

    /**
     *
     * 校验产品是否可用
     *
     * @param request
     * @param requestbody
     * @return
     */
    @RequestMapping(value = "/checkProduct", method= RequestMethod.POST)
    @ResponseBody
    public ValidateResult checkProduct(HttpServletRequest request, @RequestBody ValidateRequest requestbody) {
        ValidateResult result = new ValidateResult();

        // 参数校验
        String errorMessage = requestbody.validate();
        if (StringUtils.isNotBlank(errorMessage)) {
            result.setErrorMessage(errorMessage);
            return result;
        }

        result = orderBeforeService.checkProduct(requestbody);
        return result;
    }
}
