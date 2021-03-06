package com.dongl.servicemeeting.service.impl;


import com.dongl.servicemeeting.domian.RoomInfoLock;
import com.dongl.servicemeeting.lock.MysqlLock;
import com.dongl.servicemeeting.service.GrabService;
import com.dongl.servicemeeting.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dongliang7
 * @projectName distributed-lock-parent
 * @ClassName MysqlLockServiceImpl.java
 * @description: mysql锁业务层
 * @createTime 2022年03月21日 22:25:00
 */
@Service("mysqlLockService")
public class MysqlLockServiceImpl implements GrabService {

    @Autowired
    private MysqlLock lock;


    @Autowired
    private RoomService roomService;

    ThreadLocal<RoomInfoLock> roomLock = new ThreadLocal<>();

    @Override
    public String grabRoom(int userId, int roomId) {
        // 生成锁
        RoomInfoLock roomInfoLock = new RoomInfoLock();
        roomInfoLock.setRoomId(roomId);
        roomInfoLock.setUserId(userId);

        roomLock.set(roomInfoLock);
        lock.setRoomLockThreadLocal(roomLock);

        // 加锁
        lock.lock();

        // 执行业务
        try {

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
