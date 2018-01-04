package com.elong.nbopen.api.model.viewmodel.hotel;

import java.util.List;

/**
 * Created by user on 2017/12/14.
 */
public class ListPageResult<T> {

    /**
     * 酒店总数，默认为0
     */
    private Integer count = 0;

    /**
     * 酒店简要信息列表
     */
    private List<T> list;

    /**
     * 是否包含下一页，默认为false
     */
    private Boolean isHaveNextPage = false;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Boolean getHaveNextPage() {
        return isHaveNextPage;
    }

    public void setHaveNextPage(Boolean haveNextPage) {
        isHaveNextPage = haveNextPage;
    }
}
