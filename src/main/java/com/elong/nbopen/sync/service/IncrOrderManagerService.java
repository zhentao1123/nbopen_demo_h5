package com.elong.nbopen.sync.service;

import com.elong.nbopen.api.dao.db.IOrderDao;
import com.elong.nbopen.api.repository.HotelIncrOrderApi;
import com.elong.nbopen.api.repository.HotelOrderDetailApi;
import com.elong.nbopen.sync.dao.db.IIncrOrderManagerDao;
import com.elong.nbopen.sync.model.dao.db.IncrOrderManagerDo;
import com.elong.nbopen.sync.task.IncrOrderTask;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncrOrderManagerService {

    private Logger logger = Logger.getLogger("IncrOrderManagerService");

    @Autowired
    private IIncrOrderManagerDao incrOrderManagerDao;

//    @Autowired
//    private IOrderDao orderDao;
//
//    @Autowired
//    private HotelIncrOrderApi incrOrderApi;
//
//    @Autowired
//    private HotelOrderDetailApi hotelOrderDetailApi;

//    @Autowired
//    private IncrOrderTask incrOrderTask;

//    public IncrOrderManagerService() {
//        seizeOrderUpdateTask();
//    }

    /**
     *
     * 获取订单增量管理的信息，即mac地址为多少的机器在执行订单增量任务，其最后更新时间是多少，以及最后更新的LastId
     *
     * @return
     */
    public IncrOrderManagerDo getIncrManagerInfo() {
        try {
            return incrOrderManagerDao.getManagerInfo();
        } catch (Exception e){
            logger.error(e);
            return null;
        }
    }

    /**
     * 更新订单增量的任务，工程启动时启动
     */
    private void seizeOrderUpdateTask() {
//        IncrOrderTask task = new IncrOrderTask(incrOrderManagerDao, orderDao, incrOrderApi, hotelOrderDetailApi);
//        IncrOrderTask task = new IncrOrderTask();
//        task.start();
//        incrOrderTask.start();
    }
}
