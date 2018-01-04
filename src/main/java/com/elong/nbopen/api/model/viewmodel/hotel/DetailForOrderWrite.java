package com.elong.nbopen.api.model.viewmodel.hotel;

import java.math.BigDecimal;

/**
 * Created by user on 2017/12/21.
 */
public class DetailForOrderWrite {

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 星级
     */
    private String starRate;

    /**
     * 房型名称
     */
    private String roomName;

    /**
     * 产品名称
     */
    private String ratePlanName;

    /**
     * 付款类型
     */
    private String paymentType;

    /**
     * 宾客类型
     */
    private String customerType;

    /**
     * 入离日期描述
     */
    private String dateDescription;

    /**
     * 在店天数
     */
    private Long numberOrDays;

    /**
     * 最少预订数量
     */
    private Integer minAmount;

    /**
     * 总价
     */
    private BigDecimal totalRate;

    /**
     * 总价的货币类型
     */
    private String currencyCode;

    /**
     * 发票模式
     */
    private String invoiceMode;

    /**
     * 取消规则描述
     */
    private String cancelRule;

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getStarRate() {
        return starRate;
    }

    public void setStarRate(String starRate) {
        this.starRate = starRate;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRatePlanName() {
        return ratePlanName;
    }

    public void setRatePlanName(String ratePlanName) {
        this.ratePlanName = ratePlanName;
    }

    public String getDateDescription() {
        return dateDescription;
    }

    public void setDateDescription(String dateDescription) {
        this.dateDescription = dateDescription;
    }

    public Long getNumberOrDays() {
        return numberOrDays;
    }

    public void setNumberOrDays(Long numberOrDays) {
        this.numberOrDays = numberOrDays;
    }

    public Integer getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
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

    public String getInvoiceMode() {
        return invoiceMode;
    }

    public void setInvoiceMode(String invoiceMode) {
        this.invoiceMode = invoiceMode;
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

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
}
