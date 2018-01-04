package com.elong.nbopen.api.model.repository;

import com.alibaba.fastjson.annotation.JSONField;
import com.elong.nbopen.api.model.elong.CancelOrderResult;

public class OrderCancelResult extends BaseResult{
    @JSONField(name="Result")
    private CancelOrderResult result;

    public CancelOrderResult getResult() {
        return result;
    }
    public void setResult(CancelOrderResult result) {
        this.result = result;
    }
}
