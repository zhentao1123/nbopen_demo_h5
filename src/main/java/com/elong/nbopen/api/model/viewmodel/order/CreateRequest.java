package com.elong.nbopen.api.model.viewmodel.order;

import com.alibaba.fastjson.annotation.JSONField;
import com.elong.nbopen.api.model.elong.Invoice;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CreateRequest {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 入住日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arrivalDate;

    /**
     * 离店日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;

    /**
     * 最早到店时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date earliestArrivalTime;

    /**
     * 最晚到店时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date latestArrivalTime;

    /**
     * 付款类型
     * SelfPay-现付  Prepay-预付
     */
    private String paymentType;

    /**
     * 酒店id
     */
    private String hotelId;

    /**
     * 房型id
     */
    private String roomTypeId;

    /**
     * 产品id
     */
    private Integer ratePlanId;

    /**
     * 货币类型
     */
    private String currencyCode;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 房间数量
     */
    private Integer numberOfRooms;

    /**
     * 是否需要发票
     */
    @JSONField(name = "isNeedInvoice")
    private Boolean isNeedInvoice = false;

    /**
     * 发票信息
     */
    private Invoice invoice = null;

    /**
     * 宾客类型
     * All=统一价 Chinese =内宾价 OtherForeign =外宾价 HongKong =港澳台客人价 Japanese=日本客人价 ChinaGuest=中宾价
     */
    private String customerType;

    /**
     * 客人姓名列表
     */
    private List<String> customers;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人电话
     */
    private String contactPhone;

    /**
     * 校验参数
     *
     * @return
     */
    public String validate() {
        if (StringUtils.isBlank(userName)) {
            return "用户名不可为空";
        }
        if (arrivalDate == null || departureDate == null) {
            return "入离日期不可为空";
        }
        if (earliestArrivalTime == null || latestArrivalTime == null) {
            return "最晚到店时间不可为空";
        }
        if (latestArrivalTime.getTime() < earliestArrivalTime.getTime()) {
            return "最晚到店时间必须为下一个整点";
        }
        if (StringUtils.isBlank(hotelId)) {
            return "酒店id不可为空";
        }
        if (StringUtils.isBlank(roomTypeId)) {
            return "房型id不可为空";
        }
        if (ratePlanId == null) {
            return "产品id不可为空";
        }
        if (totalPrice == null) {
            return "总价不可为空";
        }
        if (numberOfRooms == null) {
            return "房间数量不可为空";
        }
        if (customers == null || customers.size() < numberOfRooms) {
            return "客人数量不可小于房间数量";
        }
        if (StringUtils.isBlank(contactName)) {
            return "联系人姓名不可为空";
        }
        if (StringUtils.isBlank(contactPhone)) {
            return "联系人手机号不可为空";
        }
        if (StringUtils.isBlank(paymentType) || !(paymentType.equals("SelfPay") || paymentType.equals("Prepay"))) {
            return "付款类型不可为空";
        }
        if (StringUtils.isBlank(customerType)) {
            return "宾客类型类型不可为空";
        }
        return "";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Date getEarliestArrivalTime() {
        return earliestArrivalTime;
    }

    public void setEarliestArrivalTime(Date earliestArrivalTime) {
        this.earliestArrivalTime = earliestArrivalTime;
    }

    public Date getLatestArrivalTime() {
        return latestArrivalTime;
    }

    public void setLatestArrivalTime(Date latestArrivalTime) {
        this.latestArrivalTime = latestArrivalTime;
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

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Boolean getNeedInvoice() {
        return isNeedInvoice;
    }

    public void setNeedInvoice(Boolean needInvoice) {
        isNeedInvoice = needInvoice;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public List<String> getCustomers() {
        return customers;
    }

    public void setCustomers(List<String> customers) {
        this.customers = customers;
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
