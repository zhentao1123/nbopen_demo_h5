package com.elong.nbopen.test;

import com.elong.nbopen.api.dao.db.IUserDao;
import com.elong.nbopen.api.model.dao.db.UserDo;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/test")
public class AccessController {

    private Logger logger = Logger.getLogger("AccessController");

    @Autowired
    private IUserDao userDao;

    /**
     * 用户登录注册页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method= RequestMethod.GET)
    public ModelAndView longinPage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/WEB-INF/views/test/access.jsp");
        return mv;
    }

    @RequestMapping(value = "/loginRequest", method= RequestMethod.POST)
    @ResponseBody
    public RegisterResult login(HttpServletRequest request, @RequestBody LoginRequest requestBody) {
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
        } else if (requestBody.getPassword().equals(userDo.getPassword())) {
            result.setSuccess(true);
            result.setMessage("登录成功");
        }
        return result;
    }
}
