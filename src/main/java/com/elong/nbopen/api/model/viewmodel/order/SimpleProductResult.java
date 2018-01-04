package com.elong.nbopen.api.model.viewmodel.order;

import java.math.BigDecimal;

public class SimpleProductResult {
    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 房型名称
     */
    private String roomName;

    /**
     * 产品名称
     */
    private String ratePlanName;

    /**
     * 房间数量
     */
    private Integer roomNum;

    /**
     * 付款类型
     */
    private String paymentType;

    /**
     * 入离日期描述
     */
    private String dateDescription;

    /**
     * 在店天数
     */
    private Long numberOrDays;

    /**
     * 总价
     */
    private BigDecimal totalRate;

    /**
     * 需要支付的金额
     */
    private BigDecimal payAmount;

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
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

    public BigDecimal getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(BigDecimal totalRate) {
        this.totalRate = totalRate;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
}
