package com.elong.nbopen.api.repository;

import com.elong.nbopen.api.model.elong.SubmitOrderPaymentInfoRequest;
import com.elong.nbopen.api.model.repository.OrderPayResult;
import org.springframework.stereotype.Repository;

@Repository
public class HotelOrderPayApi extends BaseApi<SubmitOrderPaymentInfoRequest, OrderPayResult>{

    @Override
    public String method() {
        return "hotel.order.pay";
    }

    @Override
    public boolean isRequiredSSL() {
        return true;
    }
}
