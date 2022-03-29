package com.dongl.servicemeeting.service.impl;

/**
 * @author dongliang7
 * @projectName distributed-lock-parent
 * @ClassName ddd.java
 * @description: TODO
 * @createTime 2022年03月21日 18:46:00
 */
import com.dongl.servicemeeting.constant.RedisKeyConstant;
import com.dongl.servicemeeting.service.GrabService;
import com.dongl.servicemeeting.service.RoomService;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author dongliang7
 * @projectName distributed-lock-parent
 * @ClassName RedLockServiceImpl.java
 * @description: 红锁业务层
 * @createTime 2022年03月20日 23:34:00
 */
@Service("redLockService")
public class RedLockServiceImpl implements GrabService {

    @Autowired
    @Qualifier("redissonRed1")
    private RedissonClient red1;
    @Autowired
    @Qualifier("redissonRed2")
    private RedissonClient red2;
    @Autowired
    @Qualifier("redissonRed3")
    private RedissonClient red3;

    @Autowired
    private RoomService roomService;

    @Override
    public String grabRoom(int userId, int roomId) {
        //生成key
        String lockKey = (RedisKeyConstant.GRAB_LOCK_ROOM_KEY_PRE + roomId).intern();

        //红锁 redis son
        RLock rLock1 = red1.getLock(lockKey);
        RLock rLock2 = red2.getLock(lockKey);
        RLock rLock3 = red3.getLock(lockKey);
        RedissonRedLock rLock = new RedissonRedLock(rLock1,rLock2,rLock3);



        try {
            rLock.lock();

//            try {
//                TimeUnit.SECONDS.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            // 此代码默认 设置key 超时时间30秒，过10秒，再延时

            boolean b = roomService.grab(userId, roomId);
            if (b) {
                System.out.println("用户:" + userId + " 执行抢占会议室成功");
            } else {
                System.out.println("用户:" + userId + " 执行抢占会议室失败");
            }
            
        } finally {
        	rLock.unlock();
        }
        return null;
    }
}