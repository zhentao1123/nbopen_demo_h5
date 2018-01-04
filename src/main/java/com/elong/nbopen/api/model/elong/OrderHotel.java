package com.elong.nbopen.api.model.elong;

import com.alibaba.fastjson.annotation.JSONField;

public class OrderHotel {
    @JSONField(name = "HotelId")
    protected String hotelId;

    @JSONField(name = "Name")
    protected String name;

    @JSONField(name = "Address")
    protected String address;

    @JSONField(name = "Phone")
    protected String phone;

    @JSONField(name = "CityName")
    protected String cityName;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
