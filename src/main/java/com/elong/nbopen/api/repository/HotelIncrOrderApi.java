package com.elong.nbopen.api.repository;

import com.elong.nbopen.api.model.elong.IncrCondition;
import com.elong.nbopen.api.model.repository.OrderIncrResult;
import org.springframework.stereotype.Repository;

@Repository
public class HotelIncrOrderApi extends BaseApi<IncrCondition, OrderIncrResult> {
    @Override
    public String method() {
        return "hotel.incr.order";
    }

    @Override
    public boolean isRequiredSSL() {
        return true;
    }
}
