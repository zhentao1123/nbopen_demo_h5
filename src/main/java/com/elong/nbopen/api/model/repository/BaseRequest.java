package com.elong.nbopen.api.model.repository;

import com.alibaba.fastjson.annotation.JSONField;
import com.elong.nbopen.api.model.elong.EnumLocal;
import com.elong.nbopen.api.model.elong.HotelListCondition;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Created by user on 2017/12/13.
 */
@XmlRootElement(name = "Condition")
public class BaseRequest<T> {

    @XmlElement(name = "Version")
    @JSONField(name = "Version")
    private double version;

    @XmlElement(name = "Local")
    @JSONField(name = "Local")
    private EnumLocal local;

    @XmlElement(name = "Request")
    @JSONField(name = "Request")
    private T request;

    public EnumLocal getLocal() {
        return this.local;
    }

    public void setLocal(EnumLocal local) {
        this.local = local;
    }

    public T getRequest() {
        return this.request;
    }

    public void setRequest(T request) {
        this.request = request;
    }

    public double getVersion() {

        return this.version;
    }

    public void setVersion(double version) {
        this.version = version;
    }
}