package com.dongl.servicemeeting.service.impl;


import com.dongl.servicemeeting.service.GrabService;
import com.dongl.servicemeeting.service.RenewGrabLockService;
import com.dongl.servicemeeting.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author dongliang7
 * @projectName distributed-lock-parent
 * @ClassName RedisLockServiceImpl.java
 * @description: redis锁业务层
 * @createTime 2022年03月20日 22:46:00
 */
@Service("redisLockService")
public class RedisLockServiceImpl implements GrabService {

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RoomService roomService;

	@Autowired
	private RenewGrabLockService renewLockService;
	
    @Override
    public String grabRoom(int userId , int roomId){
        //生成key
    	String lock = "room_"+(roomId+"");
    	/*
    	 *  情况一，如果锁没执行到释放，比如业务逻辑执行一半，运维重启服务，或 服务器挂了，没走 finally，怎么办？
    	 *  加超时时间
    	 *  setnx
    	 */
//    	boolean lockStatus = stringRedisTemplate.opsForValue().setIfAbsent(lock.intern(), userId+"");
//    	if(!lockStatus) {
//    		return null;
//    	}
    	
    	/*
    	 *  情况二：加超时时间,会有加不上的情况，运维重启
    	 */
//    	boolean lockStatus = stringRedisTemplate.opsForValue().setIfAbsent(lock.intern(), userId+"");
//    	stringRedisTemplate.expire(lock.intern(), 30L, TimeUnit.SECONDS);
//    	if(!lockStatus) {
//    		return null;
//    	}
    	
    	/*
    	 * 情况三：超时时间应该一次加，不应该分2行代码
    	 * 
    	 */
    	boolean lockStatus = stringRedisTemplate.opsForValue().setIfAbsent(lock.intern(), userId+"", 30L, TimeUnit.SECONDS);
    	// 开个子线程，原来时间N，每个n/3，去续上n
//		new Thread(() ->{
//			renewLockService.renewLock(lock.intern(), userId+"", 30);
//		}).start();
    	
    	if(!lockStatus) {
    		return null;
    	}

//		try {
//			TimeUnit.SECONDS.sleep(60);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
    	
    	try {
			System.out.println("用户:" + userId + " 执行抢占会议室逻辑");

			boolean b = roomService.grab(userId, roomId);
			if (b) {
				System.out.println("用户:" + userId + " 执行抢占会议室成功");
			} else {
				System.out.println("用户:" + userId + " 执行抢占会议室失败");
			}
            
        } finally {
        	/**
        	 * 这种释放锁有，可能释放了别人的锁。
        	 */
//        	stringRedisTemplate.delete(lock.intern());
        	
        	/**
        	 * 下面代码避免释放别人的锁
        	 */
        	if((userId+"").equals(stringRedisTemplate.opsForValue().get(lock.intern()))) {
				stringRedisTemplate.delete(lock.intern());
			}
        }
        return "ok";
    }
}