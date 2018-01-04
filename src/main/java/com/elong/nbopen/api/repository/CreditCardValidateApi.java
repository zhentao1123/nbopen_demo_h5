package com.elong.nbopen.api.repository;

import com.elong.nbopen.api.model.elong.ValidateCreditCardCondition;
import com.elong.nbopen.api.model.repository.CreditValidateResult;
import org.springframework.stereotype.Repository;

@Repository
public class CreditCardValidateApi extends BaseApi<ValidateCreditCardCondition, CreditValidateResult> {

    @Override
    public String method() {
        return "common.creditcard.validate";
    }

    @Override
    public boolean isRequiredSSL() {
        return true;
    }
}
