package com.elong.nbopen.api.model.city;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by user on 2017/12/13.
 */
public class HotCity {
    /**
     * 省份id
     */
    @JSONField(name = "pid")
    private String pid;

    /**
     * 省份名称
     */
    @JSONField(name = "pname")
    private String pname;

    /**
     * 城市id
     */
    @JSONField(name = "id")
    private String id;

    /**
     * 城市名称
     */
    @JSONField(name = "name")
    private String name;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

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
