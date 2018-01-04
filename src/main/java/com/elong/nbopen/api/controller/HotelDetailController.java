package com.elong.nbopen.api.controller;

import com.elong.nbopen.api.model.viewmodel.hotel.DetailRequest;
import com.elong.nbopen.api.model.viewmodel.hotel.DetailResult;
import com.elong.nbopen.api.service.HotelDetailService;
import com.elong.nbopen.api.util.CipherUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by user on 2017/12/18.
 */
@Controller
@RequestMapping(value = "/api/hotel")
public class HotelDetailController {

    private Logger logger = Logger.getLogger("HotelDetailController");

    @Autowired
    private HotelDetailService hotelDetailService;

    @RequestMapping(value = "/getDetail", method= RequestMethod.POST)
    @ResponseBody
    public DetailResult getDetail(HttpServletRequest request, @RequestBody DetailRequest requestBody) {
        DetailResult result = null;

        // 参数校验
        String paramValidate = requestBody.validate();
        if (StringUtils.isNotBlank(paramValidate)) {
            return new DetailResult();
        }

        result = hotelDetailService.getDetail(requestBody);
        return result;
    }

    @RequestMapping(value = "/abcd", method= RequestMethod.GET)
    @ResponseBody
    public String abbb(HttpServletRequest request) {
        String aadnflk = request.getParameter("name");

        Long time = new Date().getTime()/1000;
        String result = null;
        try {
            result = CipherUtil.desDecrypt(request.getParameter("credit"),"df7d82fe");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
