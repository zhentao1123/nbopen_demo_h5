package com.elong.nbopen.api.model.repository;

import com.alibaba.fastjson.annotation.JSONField;
import com.elong.nbopen.api.model.elong.OrderDetailResult;

public class OrderInfoResult extends BaseResult{
    @JSONField(name="Result")
    private OrderDetailResult result;

    public OrderDetailResult getResult() {
        return result;
    }
    public void setResult(OrderDetailResult result) {
        this.result = result;
    }
}
