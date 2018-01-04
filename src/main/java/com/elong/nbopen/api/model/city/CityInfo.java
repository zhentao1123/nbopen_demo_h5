package com.elong.nbopen.api.model.city;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by user on 2017/12/13.
 */
public class CityInfo {
    @JSONField(name="id")
    private String id;

    @JSONField(name="name")
    private String name;

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
}
