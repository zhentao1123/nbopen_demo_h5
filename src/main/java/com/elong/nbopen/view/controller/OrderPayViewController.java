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
 * Created by user on 2017/12/11.
 */
@Controller
@RequestMapping(value = "/view/order")
public class OrderPayViewController {

    private Logger logger = Logger.getLogger("OrderPayViewController");

    /**
     *
     * 订单支付页
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/pay", method= RequestMethod.GET)
    public ModelAndView pay(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/WEB-INF/views/order/pay.jsp");

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

        Long orderId = null;
        try {
            orderId = Long.parseLong(request.getParameter("orderId"));
        } catch (Exception e) {

        }

        mv.addObject("user", user);
        mv.addObject("orderId", orderId);
        return mv;
    }
}
