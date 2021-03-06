package com.dongl.apiuser.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author dongliang7
 * @projectName distributed-lock-parent
 * @ClassName UserClient.java
 * @description: FeignClient meeting服务接口层
 * @createTime 2022年03月21日 18:46:00
 */
@FeignClient(name = "service-meeting")
public interface UserClient {

    @GetMapping("/meeting/v1/lockRoom")
    String lockRoom(@RequestParam("userId") int userId , @RequestParam("roomId")int roomId);
}
