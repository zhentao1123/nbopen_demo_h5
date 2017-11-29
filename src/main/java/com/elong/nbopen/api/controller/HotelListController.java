package com.elong.nbopen.api.controller;

import com.elong.nbopen.api.model.dao.db.UserDo;
import com.elong.nbopen.api.service.HotelListService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HotelListController {

    @Autowired
    private HotelListService hotelListService;

    private Logger logger = Logger.getLogger("HotelListController");

    /**
     *
     * 酒店列表页
     *
     *
     * @return
     */
    @RequestMapping(value = "/list", method= RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/WEB-INF/views/hotel/list.jsp");
        mv.addObject("hello","人生苦短，我用java");
        logger.info("人生苦短，我用python");
        return mv;
    }

    @RequestMapping(value = "/user", method= RequestMethod.GET)
    @ResponseBody
    public UserDo getUserInfo(HttpServletRequest request) {

        return hotelListService.getUserInfo();
    }
}
