package com.elong.nbopen.sync.controller;

import com.elong.nbopen.sync.dao.db.IIncrOrderManagerDao;
import com.elong.nbopen.sync.model.dao.db.IncrOrderManagerDo;
import com.elong.nbopen.sync.service.IncrOrderManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/sync/manager")
public class IncrManagerController {

    @Autowired
    private IIncrOrderManagerDao incrOrderManagerDao;

    @Autowired
    private IncrOrderManagerService incrOrderManagerService;

    /**
     *
     * 获取产品基础信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getManager", method= RequestMethod.GET)
    @ResponseBody
    public IncrOrderManagerDo getManager(HttpServletRequest request) {
        IncrOrderManagerDo result = null;
        result = incrOrderManagerService.getIncrManagerInfo();
        return result;
    }
}
