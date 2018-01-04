package com.elong.nbopen.api.service;

import com.elong.nbopen.api.model.dao.db.OrderDo;
import com.elong.nbopen.api.model.elong.CancelOrderCondition;
import com.elong.nbopen.api.model.repository.OrderCancelResult;
import com.elong.nbopen.api.model.viewmodel.order.CancelRequest;
import com.elong.nbopen.api.model.viewmodel.order.CancelResult;
import com.elong.nbopen.api.repository.HotelOrderCancelApi;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderCancelService {

    private Logger logger = Logger.getLogger("OrderCancelService");

    @Autowired
    private HotelOrderCancelApi cancelApi;

    /**
     *
     * 取消订单
     *
     * @param request
     * @return
     */
    public CancelResult cancelOrder(CancelRequest request) {
        CancelResult result = new CancelResult();
        CancelOrderCondition condition = new CancelOrderCondition();
        condition.setOrderId(request.getOrderId());
        condition.setCancelCode("行程变更");

        try {
            OrderCancelResult cancelResult = cancelApi.Invoke(condition);
            if (cancelResult == null || !cancelResult.getCode().equals("0") || !cancelResult.getResult().isSuccesss()) {
                result.setReason("取消订单失败");
            } else {
                result.setSuccess(true);
                OrderDo orderDo = new OrderDo();
                orderDo.setOrderId(request.getOrderId());
                orderDo.setCancelRecieveTime(new Date());
            }
        } catch (Exception e) {
            result.setReason("取消操作异常");
            logger.error(e);
        }
        return result;
    }
}
