package com.elong.nbopen.api.model.repository;

import com.alibaba.fastjson.annotation.JSONField;
import com.elong.nbopen.api.model.elong.IncrOrderResult;

public class OrderIncrResult extends BaseResult{
    @JSONField(name="Result")
    private IncrOrderResult result;

    public IncrOrderResult getResult() {
        return result;
    }
    public void setResult(IncrOrderResult result) {
        this.result = result;
    }
}
