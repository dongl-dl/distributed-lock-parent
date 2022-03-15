package com.dongl.servicemeeting.service.impl;

import com.dongl.servicemeeting.service.GrabService;
import com.dongl.servicemeeting.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("grabJvmLockService")
public class GrabJvmLockServiceImpl implements GrabService {

	@Autowired
	private RoomService roomService;
	
	@Override
	public String grabRoom(int userId, int roomId) {
		//jvm锁
		String lock = roomId + "";

		synchronized (lock.intern()) {
			try {
				System.out.println("用户:" + userId + " 执行抢占会议室逻辑");

				boolean b = roomService.grab(userId, roomId);
				if (b) {
					System.out.println("用户:" + userId + " 执行抢占会议室成功");
				} else {
					System.out.println("用户:" + userId + " 执行抢占会议室失败");
				}
			}finally {

			}
		}
		return "ok";
	}

}
