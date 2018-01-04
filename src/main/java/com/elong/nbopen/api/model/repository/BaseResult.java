package com.elong.nbopen.api.model.repository;

import com.alibaba.fastjson.annotation.JSONField;
import com.elong.nbopen.api.model.elong.EnumLocal;
import com.elong.nbopen.api.model.elong.HotelListCondition;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Created by user on 2017/12/14.
 */
@XmlSeeAlso({HotelListCondition.class})
public class BaseResult {
    @JSONField(name="Code")
    private String code;

    // 	@XmlElement(name = "Code")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}
