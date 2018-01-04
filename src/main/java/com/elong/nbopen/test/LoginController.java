package com.elong.nbopen.test;

import com.elong.nbopen.api.dao.db.IOrderDao;
import com.elong.nbopen.api.dao.db.IUserDao;
import com.elong.nbopen.api.model.dao.db.OrderDo;
import com.elong.nbopen.api.model.dao.db.UserDo;
import com.elong.nbopen.api.util.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 本页面仅作为开发时测试登录以及添加用户使用使用，真实的用户登录、注册、在线校验等功能，需要代理自行开发
 * 另外，本工程所有接口、页面，请只在内网中使用，不要暴露在公网，否则造成的损失由代理自行承担
 * 如果使用本工程对外提供服务，请添加加代理访问层，以保证安全性
 */
@Controller
@RequestMapping(value = "/test")
public class LoginController {

    private Logger logger = Logger.getLogger("LoginController");

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IOrderDao orderDao;

    /**
     *
     * 用户登录注册页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method= RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/WEB-INF/views/test/login.jsp");
        return mv;
    }

    @RequestMapping(value = "/register", method= RequestMethod.POST)
    @ResponseBody
    @Transactional
    public RegisterResult getDetail(HttpServletRequest request, @RequestBody LoginRequest requestBody) {
        RegisterResult result = new RegisterResult();
        Map<String, Object> params = new HashMap<String, Object>();
        if (StringUtils.isBlank(requestBody.getUserName())) {
            result.setMessage("用户名不可为空");
            return result;
        }
        if (requestBody.getUserName().length() > 16) {
            result.setMessage("用户名不可超过16位");
            return result;
        }

        String regex = "^[a-z0-9A-Z]+$";
        if (!requestBody.getUserName().matches(regex)) {
            result.setMessage("用户名必须是英文和数字");
            return result;
        }
        params.put("userName", requestBody.getUserName());

        UserDo userDo = userDao.queryUserByName(params);
        if (userDo == null) {
            params.put("password", requestBody.getPassword());
            if(userDao.addUser(params)>0) {
                result.setSuccess(true);
                result.setMessage("注册用户成功");
            } else {
                result.setMessage("注册用户失败");
            }
        } else {
            result.setMessage("已存在该用户名");
        }
        return result;
    }

    @RequestMapping(value = "/access", method= RequestMethod.POST)
    @ResponseBody
    public RegisterResult access(HttpServletRequest request, @RequestBody LoginRequest requestBody) {
        RegisterResult result = new RegisterResult();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userName", requestBody.getUserName());
        UserDo userDo = userDao.queryUserByName(params);
        if (userDo == null) {
            result.setMessage("尚未注册");
        } else {
            if (userDo.getPassword().equals(requestBody.getPassword())) {
                result.setSuccess(true);
                result.setMessage("登录成功");
            } else {
                result.setMessage("密码错误");
            }

        }
        return result;
    }

    @RequestMapping(value = "/ltest", method= RequestMethod.POST)
    @ResponseBody
    public OrderDo ltest() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderId", 171882798);
        return orderDao.queryOrderByOrderId(params);
    }
}
