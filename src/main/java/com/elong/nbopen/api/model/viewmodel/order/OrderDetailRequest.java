package com.elong.nbopen.api.model.viewmodel.order;

import org.apache.commons.lang.StringUtils;

public class OrderDetailRequest {

    private String username;

    private Long orderId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String validate() {
        if (StringUtils.isBlank(username)) {
            return "用户名不可为空";
        }
        if (orderId == null) {
            return "订单编号不可为空";
        }
        return null;
    }
}
