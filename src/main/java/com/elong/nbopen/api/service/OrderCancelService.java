package com.elong.nbopen.api.service;

import com.elong.nbopen.api.dao.db.IOrderDao;
import com.elong.nbopen.api.model.dao.db.OrderDo;
import com.elong.nbopen.api.model.elong.CancelOrderCondition;
import com.elong.nbopen.api.model.repository.OrderCancelResult;
import com.elong.nbopen.api.model.viewmodel.order.CancelRequest;
import com.elong.nbopen.api.model.viewmodel.order.CancelResult;
import com.elong.nbopen.api.repository.HotelOrderCancelApi;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class OrderCancelService {

    private Logger logger = Logger.getLogger("OrderCancelService");

    @Autowired
    private HotelOrderCancelApi cancelApi;

    @Autowired
    private IOrderDao orderDao;

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
        condition.setPenaltyAmount(request.getPenaltyAmount());

        try {
            OrderCancelResult cancelResult = cancelApi.Invoke(condition);
            if (cancelResult == null || !cancelResult.getCode().equals("0") || !cancelResult.getResult().isSuccesss()) {
                String reason = cancelResult.getCode().split("\\|")[1];
                result.setPenaltyAmount(new BigDecimal(Double.parseDouble(reason.split(":")[1])));
            } else if (cancelResult != null && cancelResult.getCode().equals("0")) {
                if (cancelResult.getResult().isSuccesss()) {
                    result.setSuccess(true);
                    // 取消成功则更新接收到取消请求的时间，并且将订单状态变更为D，
                    // 如果以后有订单变化表明没有取消成功，那么再根据增量中的信息更新订单
                    // 取消成功后也不再可以支付
                    OrderDo orderDo = new OrderDo();
                    orderDo.setOrderId(request.getOrderId());
                    orderDo.setStatus("D");
                    orderDo.setNeedPay(false);
                    orderDo.setCancelRecieveTime(new Date());
                    orderDao.updateOrder(orderDo);
                } else {
                    result.setPenaltyAmount(cancelResult.getResult().getPenaltyAmount());
                }

            } else {
                result.setReason("未知原因导致取消失败");
            }
        } catch (Exception e) {
            result.setReason("取消操作异常");
            logger.error(e);
        }
        return result;
    }
}
