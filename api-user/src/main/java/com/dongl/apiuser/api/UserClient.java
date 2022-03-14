package com.dongl.apiuser.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-meeting")
public interface UserClient {

    @GetMapping("/meeting/v1/lockRoom")
    String lockRoom(@RequestParam("userId") int userId , @RequestParam("roomId")int roomId);
}
