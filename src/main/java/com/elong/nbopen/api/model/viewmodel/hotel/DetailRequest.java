package com.elong.nbopen.api.model.viewmodel.hotel;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 2017/12/18.
 */
public class DetailRequest {

    /**
     * 入住日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date arrivalDate;

    /**
     * 离店日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date departureDate;

    /**
     * 酒店id
     */
    private String hotelId;

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
            arrivalDate = new Date();
        }
        if (departureDate == null) {
            departureDate = DateUtils.addDays(new Date(), 1);
        } else if (arrivalDate.getTime() >= departureDate.getTime()) {
            return "离店日期不得小于入住日期";
        }
        if (StringUtils.isBlank(hotelId)) {
            return "酒店id不可为空";
        }

        return "";
    }
}
