package com.elong.nbopen.api.model.viewmodel.hotel;

import java.math.BigDecimal;

/**
 * Created by user on 2017/12/14.
 */
public class SimpleDetailResult {

    /**
     * 酒店id
     */
    private String hotelId;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 酒店缩略图
     */
    private String thumbnailUrl;

    /**
     * 酒店产品的最低价
     */
    private BigDecimal lowRate;

    /**
     * 最低价的货币类型
     */
    private String currencyCode;

    /**
     * 行政区名称
     */
    private String districtName;

    /**
     * 商业区名称
     */
    private String businessZoneName;

    /**
     * 点评数量
     */
    private String reviewCount;

    /**
     * 好评率，得分
     */
    private String score;

    public String getStarRate() {
        return starRate;
    }

    public void setStarRate(String starRate) {
        this.starRate = starRate;
    }

    /**
     * 星级

     */
    private String starRate;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public BigDecimal getLowRate() {
        return lowRate;
    }

    public void setLowRate(BigDecimal lowRate) {
        this.lowRate = lowRate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getBusinessZoneName() {
        return businessZoneName;
    }

    public void setBusinessZoneName(String businessZoneName) {
        this.businessZoneName = businessZoneName;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(String reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}
