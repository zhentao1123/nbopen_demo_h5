package com.elong.nbopen.api.model.dao.db;

import java.util.Date;

public class OrderDo {
    private Integer id;

    private Long orderId;

    private String affiliateConfirmationId;

    private String userId;

    private String status;

    private Long showStatus;

    private Date bookingTime;

    private String hotelId;

    private String roomTypeId;

    private Integer ratePlanId;

    /**
     * 0-现付 1-预付
     */
    private Integer paymentType;

    private String hotelName;

    private String roomName;

    private String ratePlanName;

    private Date arrivalDate;

    private Date departureDate;

    private Double totalPrice;

    private String hotelPhone;

    private String contactName;

    private String contactPhone;

    private Integer roomNum;

    /**
     * 最晚取消时间
     */
    private Date cancelTime;

    /**
     * 艺龙收到取消请求的时间
     */
    private Date cancelRecieveTime;

    private Boolean needPay;

    /**
     * -1 -- 无支付信息
     * 1 -- 等待担保或支付
     * 2 -- 担保或支付中
     * 3 -- 担保或支付成功
     * 4 -- 担保或支付失败
     */
    private Integer payStatus;

    /**
     * 需要支付的金额，担保订单即需要担保的金额
     */
    private Double payAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getAffiliateConfirmationId() {
        return affiliateConfirmationId;
    }

    public void setAffiliateConfirmationId(String affiliateConfirmationId) {
        this.affiliateConfirmationId = affiliateConfirmationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Long showStatus) {
        this.showStatus = showStatus;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Integer getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(Integer ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

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

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Date getCancelRecieveTime() {
        return cancelRecieveTime;
    }

    public void setCancelRecieveTime(Date cancelRecieveTime) {
        this.cancelRecieveTime = cancelRecieveTime;
    }

    public Boolean getNeedPay() {
        return needPay;
    }

    public void setNeedPay(Boolean needPay) {
        this.needPay = needPay;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }
}
