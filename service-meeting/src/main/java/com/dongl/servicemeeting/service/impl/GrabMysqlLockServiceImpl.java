package com.dongl.servicemeeting.service.impl;


import com.dongl.servicemeeting.lock.MysqlLock;
import com.dongl.servicemeeting.service.GrabService;
import com.dongl.servicemeeting.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("grabMysqlLockService")
public class GrabMysqlLockServiceImpl implements GrabService {

    @Autowired
    private MysqlLock lock;


    @Autowired
    private RoomService roomService;

//    ThreadLocal<TblOrderLock> orderLock = new ThreadLocal<>();

    @Override
    public String grabRoom(int userId, int roomId) {
        // 生成锁
//        TblOrderLock ol = new TblOrderLock();
//        ol.setOrderId(orderId);
//        ol.setDriverId(driverId);
//
//        orderLock.set(ol);
//        lock.setOrderLockThreadLocal(orderLock);

        // 加锁
        lock.lock();

        // 执行业务
        try {
            System.out.println("用户:" + userId + " 执行抢占会议室逻辑");

            boolean b = roomService.grab(userId, roomId);
            if (b) {
                System.out.println("用户:" + userId + " 执行抢占会议室成功");
            } else {
                System.out.println("用户:" + userId + " 执行抢占会议室失败");
            }
        }finally {
            // 释放锁
            lock.unlock();
        }

        return "ok";
    }
}