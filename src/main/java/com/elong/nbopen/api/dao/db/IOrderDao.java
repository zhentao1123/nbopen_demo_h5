package com.elong.nbopen.api.dao.db;

import com.elong.nbopen.api.model.dao.db.OrderDo;
import com.elong.nbopen.api.util.dao.db.DataSource;

import java.util.List;
import java.util.Map;

@DataSource("dataSource_mysql_order")
public interface IOrderDao {

    int addOrder(OrderDo orderDo);

    OrderDo queryOrderByOrderId(Map<String, Object> params);

    int updateOrder(OrderDo orderDo);

    int checkOrderWithUser(Map<String, Object> params);

    List<OrderDo> queryOrdersByUserId(Map<String, Object> params);
}
