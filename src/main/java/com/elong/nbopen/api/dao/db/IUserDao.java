package com.elong.nbopen.api.dao.db;

import com.elong.nbopen.api.model.dao.db.UserDo;
import com.elong.nbopen.api.util.dao.db.DataSource;

import java.util.Map;

@DataSource("dataSource_mysql_user")
public interface IUserDao {

    UserDo queryUserByName(Map<String, Object> params);

    int addUser(Map<String, Object> params);

}
