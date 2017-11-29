package com.elong.nbopen.api.dao.db;

import com.elong.nbopen.api.util.dao.db.DataSource;
import com.elong.nbopen.api.model.dao.db.UserDo;

@DataSource("dataSource_mysql_user")
public interface IH5Dao {

    UserDo getFirstInfo();
}
