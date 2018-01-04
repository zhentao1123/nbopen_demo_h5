package com.elong.nbopen.api.model.repository;

import com.alibaba.fastjson.annotation.JSONField;
import com.elong.nbopen.api.model.elong.CreateOrderResult;

public class OrderCreateResult extends BaseResult{
    @JSONField(name="Result")
    private CreateOrderResult result;

    public CreateOrderResult getResult() {
        return result;
    }
    public void setResult(CreateOrderResult result) {
        this.result = result;
    }
}
