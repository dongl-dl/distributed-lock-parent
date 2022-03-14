package com.dongl.apiuser.controller;

import com.dongl.apiuser.api.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserClient userClient;

    @GetMapping("/v1/userLock/{roomId}/{userId}")
    public String userLock(@PathVariable("roomId") int roomId , @PathVariable("roomId") int userId){
        String lockRoom = userClient.lockRoom(userId , roomId);
        return lockRoom;
    }
}
