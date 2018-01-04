package com.elong.nbopen.view.controller;

import com.elong.nbopen.api.service.CommonService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 2017/12/8.
 */
@Controller
@RequestMapping(value = "/view/order")
public class OrderWriteViewController {

    private Logger logger = Logger.getLogger("OrderWriteViewController");

    /**
     *
     * 订单填写页
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/write", method= RequestMethod.GET)
    public ModelAndView write(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/WEB-INF/views/order/write.jsp");

        String user = request.getParameter("user");
        // 如果存在user参数，那么可以访问本页面，如果没有则不允许访问
        // 由于此处仅仅是判断user参数是否存在来判断是否登录，所以不允许将此页面暴露在公网上
        if (StringUtils.isBlank(user) || user.equals("null")) {
            try {
                response.sendRedirect(CommonService.LOGIN_PAGE);
            } catch (IOException e) {
                logger.error(e);
            }
            return null;
        }

        mv.addObject("user", user);
        mv.addObject("arrivalDate", request.getParameter("arrivalDate"));
        mv.addObject("departureDate", request.getParameter("departureDate"));
        mv.addObject("hotelId", request.getParameter("hotelId"));
        mv.addObject("roomTypeId", request.getParameter("roomTypeId"));
        mv.addObject("ratePlanId", request.getParameter("ratePlanId"));
        return mv;
    }
}
