package com.elong.nbopen.api.service;

import com.elong.nbopen.api.dao.db.IH5Dao;
import com.elong.nbopen.api.model.dao.db.UserDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelListService {

    @Autowired
    private IH5Dao h5Dao;


    public UserDo getUserInfo(){
        return h5Dao.getFirstInfo();
    }
}
