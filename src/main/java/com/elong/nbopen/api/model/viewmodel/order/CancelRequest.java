package com.elong.nbopen.api.model.viewmodel.order;

public class CancelRequest {

    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String validate() {
        if (orderId == null) {
            return "订单编号不可为空";
        }
        return null;
    }
}
