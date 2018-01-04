package com.elong.nbopen.api.model.viewmodel.order;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class ValidateRequest {

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
     * 酒店id
     */
    private String hotelId;

    /**
     * 房型id
     */
    private String roomTypeID;

    /**
     * 产品id
     */
    private Integer ratePlanId;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 房间数量
     */
    private Integer numberOfRooms;

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

    public String getRoomTypeID() {
        return roomTypeID;
    }

    public void setRoomTypeID(String roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    public Integer getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(Integer ratePlanId) {
        this.ratePlanId = ratePlanId;
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

    public String validate () {
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
        if (StringUtils.isBlank(roomTypeID)) {
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
        return "";
    }
}
