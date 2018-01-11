package com.elong.nbopen.api.model.viewmodel.hotel;

import java.math.BigDecimal;

/**
 * Created by user on 2017/12/19.
 */
public class RatePlanResult {
    /**
     * 价格计划id
     */
    private Integer ratePlanId;

    /**
     * 售卖房型id
     */
    private String roomTypeId;

    /**
     * 价格计划名称
     */
    private String ratePlanName;

    /**
     * 产品价格
     */
    private BigDecimal totalRate;

    /**
     * 产品价格货币类型
     */
    private String currencyCode;

    /**
     * 取消规则描述
     */
    private String cancelRule;

    /**
     * 付款类型
     */
    private String paymentType;

    /**
     * 是否直签
     */
    private Integer cooperationType;

    public Integer getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(Integer ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRatePlanName() {
        return ratePlanName;
    }

    public void setRatePlanName(String ratePlanName) {
        this.ratePlanName = ratePlanName;
    }

    public BigDecimal getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(BigDecimal totalRate) {
        this.totalRate = totalRate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCancelRule() {
        return cancelRule;
    }

    public void setCancelRule(String cancelRule) {
        this.cancelRule = cancelRule;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getCooperationType() {
        return cooperationType;
    }

    public void setCooperationType(Integer cooperationType) {
        this.cooperationType = cooperationType;
    }
}
