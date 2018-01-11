package com.elong.nbopen.api.model.viewmodel.hotel;

import java.util.List;

/**
 * Created by user on 2017/12/19.
 */
public class DetailResult {

    /**
     * 酒店id
     */
    private String hotelId;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 星级
     */
    private String starRate;

    /**
     * 酒店地址
     */
    private String address;

    /**
     * 酒店电话
     */
    private String hotelPhone;

    /**
     * 酒店设施
     */
    private String facilities;

    /**
     * 酒店服务
     */
    private String generalAmenities;

    /**
     * 交通状况
     */
    private String traffic;


    /**
     * 房型列表
     */
    private List<RoomResult> rooms;

    /**
     * 图片列表
     */
    private List<String> images;

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

    public String getStarRate() {
        return starRate;
    }

    public void setStarRate(String starRate) {
        this.starRate = starRate;
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

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getGeneralAmenities() {
        return generalAmenities;
    }

    public void setGeneralAmenities(String generalAmenities) {
        this.generalAmenities = generalAmenities;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public List<RoomResult> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomResult> rooms) {
        this.rooms = rooms;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
