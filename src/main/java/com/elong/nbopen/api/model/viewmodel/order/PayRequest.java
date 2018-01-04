package com.elong.nbopen.api.model.viewmodel.order;

import com.alibaba.fastjson.annotation.JSONField;
import com.elong.nbopen.api.model.elong.CreditCard;

import java.math.BigDecimal;

public class PayRequest {

    @JSONField(name = "OrderId")
    private long orderId;

    @JSONField(name = "CreditCard")
    private CreditCard creditCard;

    @JSONField(name = "PayAmount")
    private BigDecimal payAmount;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String validate() {
        if (creditCard == null) {
            return "信用卡字段不可为空";
        }
        if (payAmount == null) {
            return "支付金额字段不可为空";
        }
        return null;
    }
}
