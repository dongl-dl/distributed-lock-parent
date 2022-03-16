package com.dongl.servicemeeting.lock;


import com.dongl.servicemeeting.domian.RoomInfoLock;
import com.dongl.servicemeeting.mapper.RoomInfoLockMaaper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Service
@Data
public class MysqlLock implements Lock {

	@Autowired
	private RoomInfoLockMaaper mapper;
	
	private ThreadLocal<RoomInfoLock> roomLockThreadLocal ;

	@Override
	public void lock() {
		// 1、尝试加锁
		if(tryLock()) {
			System.out.println("尝试加锁");
			return;
		}
		// 2.休眠
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 3.递归再次调用
		lock();
	}
	
	/**
	 * 	非阻塞式加锁，成功，就成功，失败就失败。直接返回
	 */
	@Override
	public boolean tryLock() {
		try {
			RoomInfoLock roomInfoLock = roomLockThreadLocal.get();
			mapper.insertSelective(roomInfoLock);
			System.out.println("加锁对象："+roomLockThreadLocal.get());
			return true;
		}catch (Exception e) {
			return false;
		}
		
		
	}
	
	@Override
	public void unlock() {
		mapper.deleteByPrimaryKey(roomLockThreadLocal.get().getRoomId());
		System.out.println("解锁对象："+roomLockThreadLocal.get());
		roomLockThreadLocal.remove();
	}

	@Override
	public void lockInterruptibly(){
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit){
		return false;
	}


	@Override
	public Condition newCondition() {
		return null;
	}

}
