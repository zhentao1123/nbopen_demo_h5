package com.elong.nbopen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HotelListController {

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
        return mv;
    }
}
