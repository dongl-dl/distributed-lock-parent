package com.dongl.servicemeeting.service.impl;

import com.dongl.servicemeeting.service.GrabService;
import com.dongl.servicemeeting.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("grabNoLockService")
public class GrabNoLockServiceImpl implements GrabService {
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
