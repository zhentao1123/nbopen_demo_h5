package com.elong.nbopen.api.model.city;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by user on 2017/12/13.
 */
public class ProvinceInfo {
    @JSONField(name="id")
    private String id;

    @JSONField(name="name")
    private String name;

    @JSONField(name="city")
    private List<CityInfo> city;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityInfo> getCity() {
        return city;
    }

    public void setCity(List<CityInfo> city) {
        this.city = city;
    }
}
