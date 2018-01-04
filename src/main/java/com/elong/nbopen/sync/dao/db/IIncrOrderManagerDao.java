package com.elong.nbopen.sync.dao.db;

import com.elong.nbopen.api.util.dao.db.DataSource;
import com.elong.nbopen.sync.model.dao.db.IncrOrderManagerDo;

import java.util.Map;

@DataSource("dataSource_mysql_incr_order_manager")
public interface IIncrOrderManagerDao {

    IncrOrderManagerDo getManagerInfo();

    int updateBeatTime(Map<String, Object> params);

    int updateMacAddr(Map<String, Object> params);
}
