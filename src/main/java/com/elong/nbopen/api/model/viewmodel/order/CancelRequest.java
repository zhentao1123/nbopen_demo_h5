package com.elong.nbopen.api.model.viewmodel.order;

import java.math.BigDecimal;

public class CancelRequest {

    private Long orderId;

    private BigDecimal penaltyAmount;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(BigDecimal penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public String validate() {
        if (orderId == null) {
            return "订单编号不可为空";
        }
        if (penaltyAmount == null) {
            return "罚金字段不为为空";
        }
        return null;
    }
}
