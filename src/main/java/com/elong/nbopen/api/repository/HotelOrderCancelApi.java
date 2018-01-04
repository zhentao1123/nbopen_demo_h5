package com.elong.nbopen.api.repository;

import com.elong.nbopen.api.model.elong.CancelOrderCondition;
import com.elong.nbopen.api.model.repository.OrderCancelResult;
import org.springframework.stereotype.Repository;

@Repository
public class HotelOrderCancelApi extends BaseApi<CancelOrderCondition, OrderCancelResult> {
    @Override
    public String method() {
        return "hotel.order.cancel";
    }

    @Override
    public boolean isRequiredSSL() {
        return true;
    }
}