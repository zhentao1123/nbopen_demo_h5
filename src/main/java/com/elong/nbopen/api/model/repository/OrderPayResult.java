package com.elong.nbopen.api.model.repository;

import com.alibaba.fastjson.annotation.JSONField;
import com.elong.nbopen.api.model.elong.SubmitOrderPaymentInfoResponse;

public class OrderPayResult extends BaseResult{
    @JSONField(name="Result")
    private SubmitOrderPaymentInfoResponse result;

    public SubmitOrderPaymentInfoResponse getResult() {
        return result;
    }
    public void setResult(SubmitOrderPaymentInfoResponse result) {
        this.result = result;
    }
}
