package com.elong.nbopen.api.model.repository;

import com.alibaba.fastjson.annotation.JSONField;
import com.elong.nbopen.api.model.elong.ValidateResult;

/**
 * Created by user on 2017/12/21.
 */
public class DataValidateResult extends BaseResult{
    @JSONField(name="Result")
    private ValidateResult result;

    public ValidateResult getResult() {
        return result;
    }
    public void setResult(ValidateResult result) {
        this.result = result;
    }

    public DataValidateResult(){
        super();
    }
}
