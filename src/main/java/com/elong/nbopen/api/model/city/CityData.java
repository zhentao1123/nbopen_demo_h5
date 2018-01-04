package com.elong.nbopen.api.model.city;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by user on 2017/12/13.
 */
public class CityData {

    /**
     * 热门城市
     */
    @JSONField(name = "hot")
    private List<HotCity> hot;

    /**
     * 省份城市信息
     */
    @JSONField(name = "province")
    private List<ProvinceInfo> province;

    public List<HotCity> getHot() {
        return hot;
    }

    public void setHot(List<HotCity> hot) {
        this.hot = hot;
    }

    public List<ProvinceInfo> getProvince() {
        return province;
    }

    public void setProvince(List<ProvinceInfo> province) {
        this.province = province;
    }
}
