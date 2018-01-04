package com.elong.nbopen.api.model.repository;

import com.alibaba.fastjson.annotation.JSONField;
import com.elong.nbopen.api.model.elong.HotelList;

/**
 * Created by user on 2017/12/19.
 */
public class HotelDetailResult extends BaseResult {

    @JSONField(name = "Result")
    private HotelList result;

    public HotelList getResult() {
        return result;
    }

    public void setResult(HotelList result) {
        this.result = result;
    }

    public HotelDetailResult(){
        super();
    }
}
