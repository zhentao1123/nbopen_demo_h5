package com.elong.nbopen.api.model.viewmodel.hotel;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by user on 2017/12/19.
 */
public class RoomResult {
    /**
     * 房型id
     */
    private String roomId;

    /**
     * 房型名称
     */
    private String name;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 房型描述
     */
    private String description;

    /**
     * 房型最低价格
     */
    private BigDecimal lowRate;

    /**
     * 房型最低价格货币类型
     */
    private String currencyCode;

    private List<RatePlanResult> ratePlanList;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getLowRate() {
        return lowRate;
    }

    public void setLowRate(BigDecimal lowRate) {
        this.lowRate = lowRate;
    }

    public List<RatePlanResult> getRatePlanList() {
        return ratePlanList;
    }

    public void setRatePlanList(List<RatePlanResult> ratePlanList) {
        this.ratePlanList = ratePlanList;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
