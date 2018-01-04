package com.elong.nbopen.api.model.viewmodel.order;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 2017/12/21.
 */
public class WriteRequest {

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
     * 酒店id
     */
    private String hotelId;

    /**
     * 房型编号
     */
    private String roomTypeId;

    /**
     * 产品编号
     */
    private Integer ratePlanId;

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

    /**
     *
     * 校验入参
     *
     * @return
     */
    public String validate() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        if (arrivalDate != null && today.getTime().getTime() > arrivalDate.getTime()) {
            return "入住日期不得小于当前日期";
        } else if (arrivalDate == null) {
            return "入住日期不可为空";
        }
        if (departureDate == null) {
            return "离店日期不可为空";
        } else if (arrivalDate.getTime() >= departureDate.getTime()) {
            return "离店日期不得小于入住日期";
        }
        if (StringUtils.isBlank(hotelId)) {
            return "酒店id不可为空";
        }
        if (StringUtils.isBlank(roomTypeId)) {
            return "房型编号不可为空";
        }
        if (ratePlanId == null) {
            return "产品id不可为空";
        }
        return "";
    }
}
