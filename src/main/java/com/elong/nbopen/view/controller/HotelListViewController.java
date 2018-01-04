package com.elong.nbopen.view.controller;

import com.elong.nbopen.api.service.CommonService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/view/hotel")
public class HotelListViewController {

    private Logger logger = Logger.getLogger("HotelListViewController");

    /**
     *
     * 酒店列表页
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/list", method= RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/WEB-INF/views/hotel/list.jsp");
        String user = request.getParameter("user");
        // 登录信息
        if (StringUtils.isBlank(user) || user.equals("null")) {
            mv.addObject("loginPage", CommonService.LOGIN_PAGE);
        } else {
            mv.addObject("user", user);
        }
        return mv;
    }
}
