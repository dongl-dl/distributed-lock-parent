package com.dongl.servicemeeting.controller;



import com.dongl.servicemeeting.service.GrabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;



/**
 * @author dongliang7
 * @projectName distributed-lock-parent
 * @ClassName MeetingController.java
 * @description: 会议控制层
 * @createTime 2022年03月18日 00:28:00
 */

@RestController
@RequestMapping("/meeting")
public class MeetingController {

    @Autowired
    /**
     * 无锁
     */
    @Qualifier("noLockService")

    /**
     * jvm锁
     */
//    @Qualifier("jvmLockService")

    /**
     * mysql锁
     */
//    @Qualifier("mysqlLockService")

    /**
     * 手写redis
     */
//    @Qualifier("redisLockService")

    /**
     * RedisSon
     */
//    @Qualifier("redisSonLockService")

    /**
     * 红锁
     */
//    @Qualifier("redLockService")
    private GrabService grabService;

    @GetMapping("/v1/lockRoom")
    public String lockRoom(@RequestParam("userId") int userId , @RequestParam("roomId")int roomId){
        System.out.println("用户："+ userId + "  请求进入");
        grabService.grabRoom(userId,roomId);
        return "ok";
    }
}
