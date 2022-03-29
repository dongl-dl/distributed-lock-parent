package com.dongl.servicemeeting.service.impl;

import com.dongl.servicemeeting.service.GrabService;
import com.dongl.servicemeeting.service.RoomService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author dongliang7
 * @projectName distributed-lock-parent
 * @ClassName RedissonLockServiceImpl.java
 * @description: redisson锁业务层
 * @createTime 2022年03月20日 22:23:00
 */
@Service("redisSonLockService")
public class RedissonLockServiceImpl implements GrabService {

    @Autowired
//    @Qualifier("redissonClient")
    private RedissonClient redissonClient;

    @Autowired
    private RoomService roomService;
	
    @Override
    public String grabRoom(int userId, int roomId) {
        //生成key
    	String lock = "room_"+(roomId+"");
    	
    	RLock rlock = redissonClient.getLock(lock.intern());

        try {
    		// 此代码默认 设置key 超时时间30秒，过10秒，再延时
    		rlock.lock();

            try {
                TimeUnit.MINUTES.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("用户:" + userId + " 执行抢占会议室逻辑");

            boolean b = roomService.grab(userId, roomId);
            if (b) {
                System.out.println("用户:" + userId + " 执行抢占会议室成功");
            } else {
                System.out.println("用户:" + userId + " 执行抢占会议室失败");
            }
            
        } finally {
        	rlock.unlock();
        }
        return "ok";
    }
}