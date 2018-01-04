package com.elong.nbopen.api.repository;

import com.elong.nbopen.api.model.elong.ValidateCondition;
import com.elong.nbopen.api.model.repository.DataValidateResult;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 2017/12/21.
 */
@Repository
public class HotelDataValidateApi extends BaseApi<ValidateCondition, DataValidateResult> {

    @Override
    public String method() {
        return "hotel.data.validate";
    }

    @Override
    public boolean isRequiredSSL() {
        return false;
    }

}
