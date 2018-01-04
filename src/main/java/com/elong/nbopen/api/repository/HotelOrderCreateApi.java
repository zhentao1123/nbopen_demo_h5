package com.elong.nbopen.api.repository;

import com.elong.nbopen.api.model.elong.CreateOrderCondition;
import com.elong.nbopen.api.model.repository.OrderCreateResult;
import org.springframework.stereotype.Repository;

@Repository
public class HotelOrderCreateApi extends BaseApi<CreateOrderCondition, OrderCreateResult> {
    @Override
    public String method() {
        return "hotel.order.create";
    }

    @Override
    public boolean isRequiredSSL() {
        return true;
    }
}