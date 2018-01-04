package com.elong.nbopen.sync.model.dao.db;

import java.util.Date;

public class IncrOrderManagerDo {
    /**
     * 数据库自增id
     */
    private Integer id;

    /**
     * 最后一次更新的服务器mac地址
     */
    private String macAddr;

    /**
     * 最后一次更新的心跳时间
     */
    private Date beatTime;

    /**
     * 最后一次更新的LastId
     */
    private Long lastId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public Date getBeatTime() {
        return beatTime;
    }

    public void setBeatTime(Date beatTime) {
        this.beatTime = beatTime;
    }

    public Long getLastId() {
        return lastId;
    }

    public void setLastId(Long lastId) {
        this.lastId = lastId;
    }
}
