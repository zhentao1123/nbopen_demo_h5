package com.elong.nbopen.api.model.viewmodel.order;

import java.util.List;

public class ListResult {

    private List<SimpleOrderResult> orders;

    private String message;

    public List<SimpleOrderResult> getOrders() {
        return orders;
    }

    public void setOrders(List<SimpleOrderResult> orders) {
        this.orders = orders;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
