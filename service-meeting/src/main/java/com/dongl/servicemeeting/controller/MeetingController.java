package com.dongl.servicemeeting.controller;



import com.dongl.servicemeeting.service.GrabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

    @Autowired
    // 无锁
//    @Qualifier("grabNoLockService")
    // jvm锁
//    @Qualifier("grabJvmLockService")
    // mysql锁
//    @Qualifier("grabMysqlLockService")
    // 手写redis
//    @Qualifier("grabRedisLockService")
    //单个redisson
//    @Qualifier("grabRedisRedissonService")
    // 红锁
//    @Qualifier("grabRedisRedissonRedLockLockService")
    private GrabService grabService;

    @GetMapping("/v1/lockRoom")
    public String lockRoom(@RequestParam("userId") int userId , @RequestParam("roomId")int roomId){
        System.out.println("userId:"+userId+",roomId:"+roomId);

        grabService.grabRoom(userId,roomId);
        return "ok";
    }
}
