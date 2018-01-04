package com.elong.nbopen.api.repository;

import com.elong.nbopen.api.model.elong.OrderIdCondition;
import com.elong.nbopen.api.model.repository.OrderInfoResult;
import org.springframework.stereotype.Repository;

@Repository
public class HotelOrderDetailApi extends BaseApi<OrderIdCondition, OrderInfoResult>{

    @Override
    public String method() {
        return "hotel.order.detail";
    }

    @Override
    public boolean isRequiredSSL() {
        return true;
    }
}
