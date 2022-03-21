package com.dongl.apiuser.controller;

import com.dongl.apiuser.api.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dongliang7
 * @projectName distributed-lock-parent
 * @ClassName userController.java
 * @description: 用户控制层
 * @createTime 2022年03月17日 22:46:00
 */
@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserClient userClient;

    /**
     * 模拟用户抢占会议室
     * @param roomId 会议室id
     * @param userId 用户id
     * @return
     */
    @GetMapping("/v1/userLock/{roomId}/{userId}")
    public String userLock(@PathVariable("roomId") int roomId ,@PathVariable("userId")int userId){
        System.out.println(userId);
        String lockRoom = userClient.lockRoom(userId , roomId);
        return lockRoom;
    }
}
