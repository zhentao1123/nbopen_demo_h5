package com.elong.nbopen.api.model.repository;

import com.alibaba.fastjson.annotation.JSONField;
import com.elong.nbopen.api.model.elong.HotelList;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by user on 2017/12/14.
 */
@XmlRootElement(name = "ApiResult")
public class HotelListResult extends  BaseResult{

    @JSONField(name="Result")
    private HotelList result;

    public HotelList getResult() {
        return result;
    }
    public void setResult(HotelList result) {
        this.result = result;
    }

    public HotelListResult(){
        super();
    }
}
