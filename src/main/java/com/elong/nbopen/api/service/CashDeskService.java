package com.elong.nbopen.api.service;

import com.elong.nbopen.api.model.elong.CashDeskResponse;
import com.elong.nbopen.api.model.elong.CashDestRequest;
import com.elong.nbopen.api.model.repository.CashDeskResult;
import com.elong.nbopen.api.model.viewmodel.order.GetCashDeskRequest;
import com.elong.nbopen.api.repository.GetCashDeskApi;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 修改历史:											<br>
 * 修改日期    		修改人员   	版本	 		修改内容<br>
 * -------------------------------------------------<br>
 * 2018/1/22 14:20  qianqian.xu     1.0    	初始化创建<br>
 * </p>
 *
 * @author qianqian.xu
 * @version 1.0
 * @since JDK1.7
 */
@Service
public class CashDeskService {

    private Logger logger = Logger.getLogger("HotelListService");

    @Autowired
    private GetCashDeskApi cashDeskApi;

    /**
     * 获取收银台地址
     *
     * @param request
     * @return
     */
    public CashDeskResponse getCashDesk(GetCashDeskRequest request) {
        CashDestRequest condition = new CashDestRequest();
        condition.setOrderId(request.getOrderId());
        condition.setAmount(request.getAmount());
        condition.setSuccessUrl(request.getSuccessUrl());
        condition.setCancelUrl(request.getCancelUrl());
        condition.setErrorUrl(request.getErrorUrl());
        CashDeskResponse result = new CashDeskResponse();
        CashDeskResult cashDeskResult = null;
        try {
            cashDeskResult = cashDeskApi.Invoke(condition);
            if (cashDeskResult != null && cashDeskResult.getCode() != null && cashDeskResult.getCode().equals("0")) {
                if (StringUtils.isNotBlank(cashDeskResult.getResult().getErrorMessage())) {
                    result.setErrorMessage(cashDeskResult.getResult().getErrorMessage());
                } else {
                    result.setPayUrl(cashDeskResult.getResult().getPayUrl());
                }
            } else {
                result.setErrorMessage("获取收银台失败");
            }
        } catch (Exception e) {
            logger.error(e);
            result.setErrorMessage("获取收银台异常");
        }

        return result;
    }
}
