package com.elong.nbopen.api.model.repository;

import com.alibaba.fastjson.annotation.JSONField;
import com.elong.nbopen.api.model.elong.ValidateCreditCardResult;

public class CreditValidateResult extends BaseResult{
    @JSONField(name="Result")
    private ValidateCreditCardResult result;

    public ValidateCreditCardResult getResult() {
        return result;
    }
    public void setResult(ValidateCreditCardResult result) {
        this.result = result;
    }
}