package com.elong.nbopen.api.repository;

import com.elong.nbopen.api.model.elong.HotelListCondition;
import com.elong.nbopen.api.model.repository.HotelListResult;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 2017/12/14.
 */
@Repository
public class HotelListApi extends BaseApi<HotelListCondition, HotelListResult>{

    @Override
    public String method() {
        return "hotel.list";
    }

    @Override
    public boolean isRequiredSSL() {
        return false;
    }
}
