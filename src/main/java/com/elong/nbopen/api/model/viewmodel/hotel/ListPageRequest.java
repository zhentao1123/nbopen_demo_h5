package com.elong.nbopen.api.model.viewmodel.hotel;

import com.elong.nbopen.api.model.elong.EnumSortType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.StringUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 2017/12/14.
 */
public class ListPageRequest {

    /**
     * 入职日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date arrivalDate;

    /**
     * 离店日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date departureDate;

    /**
     * 城市编码
     */
    private String cityId;

    /**
     * 查询文本
     */
    private String queryText;


    /**
     * 查询星级
     */
    private String starRate;

    /**
     * 最低价格
     */
    private Integer lowRate;

    /**
     * 最高价格
     */
    private Integer highRate;

    /**
     * 排序方式
     */
    private EnumSortType sortType;

    /**
     * 页码
     */
    private Integer pageIndex;

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

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

    public String getStarRate() {
        return starRate;
    }

    public void setStarRate(String starRate) {
        this.starRate = starRate;
    }

    public Integer getLowRate() {
        return lowRate;
    }

    public void setLowRate(Integer lowRate) {
        this.lowRate = lowRate;
    }

    public Integer getHighRate() {
        return highRate;
    }

    public void setHighRate(Integer highRate) {
        this.highRate = highRate;
    }

    public EnumSortType getSortType() {
        return sortType;
    }

    public void setSortType(EnumSortType sortType) {
        this.sortType = sortType;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
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
        if (arrivalDate == null) {
            return "入住日期不可为空";
        } else if (today.getTime().getTime() > arrivalDate.getTime()) {
            return "入住日期不得小于当前日期";
        }
        if (departureDate == null) {
            return "离店日期不可为空";
        } else if (arrivalDate.getTime() >= departureDate.getTime()) {
            return "离店日期不得小于入住日期";
        }
        if (StringUtils.isBlank(cityId)) {
            return "城市不可为空";
        }
        if (pageIndex == null) {
            return "页码不可为空";
        } else if (pageIndex < 1) {
            return "页码不可小于1";
        }
        if (lowRate != null && highRate != null) {
            if (lowRate == -1 || highRate == -1) {
                lowRate = null;
                highRate = null;
            }
        }

        return "";
    }
}
