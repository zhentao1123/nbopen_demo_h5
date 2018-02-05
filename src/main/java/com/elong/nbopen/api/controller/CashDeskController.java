package com.elong.nbopen.api.controller;

import com.elong.nbopen.api.model.elong.CashDeskResponse;
import com.elong.nbopen.api.model.elong.CashDestRequest;
import com.elong.nbopen.api.model.viewmodel.order.GetCashDeskRequest;
import com.elong.nbopen.api.model.viewmodel.order.PayRequest;
import com.elong.nbopen.api.model.viewmodel.order.PayResult;
import com.elong.nbopen.api.service.CashDeskService;
import com.elong.nbopen.api.service.CommonService;
import com.elong.nbopen.api.util.CommonsUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 修改历史:											<br>
 * 修改日期    		修改人员   	版本	 		修改内容<br>
 * -------------------------------------------------<br>
 * 2018/1/22 14:42  qianqian.xu     1.0    	初始化创建<br>
 * </p>
 *
 * @author qianqian.xu
 * @version 1.0
 * @since JDK1.7
 */
@Controller
@RequestMapping(value = "/api/order")
public class CashDeskController {

    private Logger logger = Logger.getLogger("CashDeskController");

    @Autowired
    private CashDeskService cashDeskService;

    @RequestMapping(value = "/getCashDesk", method = RequestMethod.POST)
    @ResponseBody
    public CashDeskResponse getCashDesk(HttpServletRequest request, @RequestBody GetCashDeskRequest requestbody) {
        CashDeskResponse result = null;

        // 参数校验
        String errorMsg = requestbody.validate();
        if (StringUtils.isNotBlank(errorMsg)) {
            result = new CashDeskResponse();
            result.setErrorMessage(errorMsg);
            return result;
        }

        String cancelUrl = CommonService.ORDER_DOMAIN + "?user=" + request.getParameter("user") + "&orderId=" + requestbody.getOrderId();
        requestbody.setSuccessUrl(cancelUrl);
        requestbody.setCancelUrl(cancelUrl);
        requestbody.setErrorUrl(cancelUrl);

        result = cashDeskService.getCashDesk(requestbody);
        return result;
    }
}