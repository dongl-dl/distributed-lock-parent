package com.dongl.servicemeeting.service.impl;

import com.dongl.servicemeeting.service.GrabService;
import com.dongl.servicemeeting.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dongliang7
 * @projectName distributed-lock-parent
 * @ClassName NoLockServiceImpl.java
 * @description: 无锁业务层
 * @createTime 2022年03月20日 22:15:00
 */
@Service("noLockService")
public class NoLockServiceImpl implements GrabService {
    @Autowired
    private RoomService roomService;

    @Override
    public String grabRoom(int userId, int roomId) {

        System.out.println("用户:"+ userId +" 执行抢占会议室逻辑");

        boolean b = roomService.grab(userId, roomId);
        if(b) {
            System.out.println("用户:"+userId+" 执行抢占会议室成功");
        }else {
            System.out.println("用户:"+userId+" 执行抢占会议室失败");
        }

        return "ok";
    }
}
