package com.elong.nbopen.api.util.dao.db;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class ByKeyRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceKeyHolder.getDataSourceKey();
    }

}
