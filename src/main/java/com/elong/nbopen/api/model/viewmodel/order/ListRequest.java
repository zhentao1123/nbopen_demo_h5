package com.elong.nbopen.api.model.viewmodel.order;

import org.apache.commons.lang.StringUtils;

public class ListRequest {

    private String username;

    private Integer page;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String validate() {
        if (StringUtils.isBlank(username)) {
            return "用户名不可为空";
        }
        if (page == null) {
            page = 1;
        }
        return null;
    }
}
