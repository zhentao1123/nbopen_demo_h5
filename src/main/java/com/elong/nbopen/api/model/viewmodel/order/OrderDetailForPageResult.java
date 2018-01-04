package com.elong.nbopen.api.model.viewmodel.order;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDetailForPageResult {

    private boolean success;

    private String message;

    private Long orderId;

    private String paymentType;

    private String showStatus;

    private boolean bPayable;

    @JSONField(format = "MM月dd日 HH:mm")
    @JsonFormat(pattern = "MM月dd日 HH:mm",timezone="GMT+8")
    private Date lastPayTime;

    private boolean bCancel;

    private String hotelId;

    private String hotelName;

    private String address;

    private String hotelPhone;

    @JSONField(format = "MM月dd日")
    @JsonFormat(pattern = "MM月dd日",timezone="GMT+8")
    private Date arrivalDate;

    @JSONField(format = "MM月dd日")
    @JsonFormat(pattern = "MM月dd日",timezone="GMT+8")
    private Date departureDate;

    private Integer numberOfDays;

    private String roomName;

    private Integer numberOfRooms;

    @JSONField(format = "MM月dd日 HH:mm")
    @JsonFormat(pattern = "MM月dd日 HH:mm",timezone="GMT+8")
    private Date lastArrivalTime;

    private String customerNames;

    private String contactPhone;

    private String valueAdds;

    private String invoiceStatus;

    private String invoiceUrl;

    @JSONField(format = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date bookingDate;

    private String currencyCode;

    private BigDecimal totalPrice;

    private BigDecimal penalty;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(String showStatus) {
        this.showStatus = showStatus;
    }

    public Date getLastPayTime() {
        return lastPayTime;
    }

    public void setLastPayTime(Date lastPayTime) {
        this.lastPayTime = lastPayTime;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
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

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Date getLastArrivalTime() {
        return lastArrivalTime;
    }

    public void setLastArrivalTime(Date lastArrivalTime) {
        this.lastArrivalTime = lastArrivalTime;
    }

    public String getCustomerNames() {
        return customerNames;
    }

    public void setCustomerNames(String customerNames) {
        this.customerNames = customerNames;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getInvoiceUrl() {
        return invoiceUrl;
    }

    public void setInvoiceUrl(String invoiceUrl) {
        this.invoiceUrl = invoiceUrl;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getPenalty() {
        return penalty;
    }

    public void setPenalty(BigDecimal penalty) {
        this.penalty = penalty;
    }

    public boolean isbPayable() {
        return bPayable;
    }

    public void setbPayable(boolean bPayable) {
        this.bPayable = bPayable;
    }

    public boolean isbCancel() {
        return bCancel;
    }

    public void setbCancel(boolean bCancel) {
        this.bCancel = bCancel;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getValueAdds() {
        return valueAdds;
    }

    public void setValueAdds(String valueAdds) {
        this.valueAdds = valueAdds;
    }
}
