package com.dongl.servicemeeting.service.impl;

import com.dongl.servicemeeting.service.RenewGrabLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author dongliang7
 * @projectName distributed-lock-parent
 * @ClassName RenewLockServiceImpl.java
 * @description: 手写redis锁续期
 * @createTime 2022年03月21日 23:34:00
 */
@Service
public class RenewLockServiceImpl implements RenewGrabLockService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    @Async
    public void renewLock(String key, String value, int time) {
        String v = redisTemplate.opsForValue().get(key);
        //判断是自己加的锁
        if (null != v && v.equals(value)){
            int sleepTime = time / 3;
            try {
                Thread.sleep(sleepTime * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            redisTemplate.expire(key,time,TimeUnit.SECONDS);
            renewLock(key,value,time);
        }
    }
}