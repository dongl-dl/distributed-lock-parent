package com.dongl.apiuser.controller;

import com.dongl.apiuser.api.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserClient userClient;

    @GetMapping("/v1/userLock/{roomId}")
    public String userLock(@PathVariable("roomId") int roomId , @RequestParam("userId") int userId){
        String lockRoom = userClient.lockRoom(userId , roomId);
        return lockRoom;
    }
}
