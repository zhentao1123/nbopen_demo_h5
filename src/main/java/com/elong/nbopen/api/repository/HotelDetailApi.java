package com.elong.nbopen.api.repository;

import com.elong.nbopen.api.model.elong.HotelDetailCondition;
import com.elong.nbopen.api.model.repository.HotelDetailResult;
import org.springframework.stereotype.Repository;

/**
 * Created by user on 2017/12/19.
 */
@Repository
public class HotelDetailApi extends BaseApi<HotelDetailCondition, HotelDetailResult>{

    @Override
    public String method() {
        return "hotel.detail";
    }

    @Override
    public boolean isRequiredSSL() {
        return false;
    }
}
