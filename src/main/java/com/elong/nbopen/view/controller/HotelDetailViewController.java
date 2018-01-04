package com.elong.nbopen.view.controller;

import com.elong.nbopen.api.service.CommonService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 2017/12/6.
 */
@Controller
@RequestMapping(value = "/view/hotel")
public class HotelDetailViewController {

    private Logger logger = Logger.getLogger("HotelDetailViewController");

    /**
     *
     * 酒店详情页
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/detail", method= RequestMethod.GET)
    public ModelAndView detail(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/WEB-INF/views/hotel/detail.jsp");

        String user = request.getParameter("user");
        // 登录信息
        if (StringUtils.isBlank(user) || user.equals("null")) {
            mv.addObject("loginPage", CommonService.LOGIN_PAGE);
        } else {
            mv.addObject("user", user);
        }

        String hotelId = request.getParameter("hotelId");
        String arrivalDate = request.getParameter("arrivalDate");
        String departureDate = request.getParameter("departureDate");
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");


        if (StringUtils.isBlank(arrivalDate) || arrivalDate.equals("null")) {
            Date today = new Date();
            mv.addObject("initArrivalDate", formatter.format(today));
        } else {
            mv.addObject("initArrivalDate", arrivalDate);
        }
        if (StringUtils.isBlank(departureDate) || departureDate.equals("null")) {
            Date tommorow = DateUtils.addDays(new Date(), 1);
            mv.addObject("initDepartureDate", formatter.format(tommorow));
        } else {
            mv.addObject("initDepartureDate", departureDate);
        }

        mv.addObject("hotelId", hotelId);
        return mv;
    }
}
