package com.elong.nbopen.view.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/view")
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
        mv.addObject("hello","人生苦短，我用C");
        logger.info("人生苦短，我用python");
        return mv;
    }
}
