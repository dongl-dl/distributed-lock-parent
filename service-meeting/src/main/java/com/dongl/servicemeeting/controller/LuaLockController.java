package com.dongl.servicemeeting.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/lua")
public class LuaLockController {

    @Resource(name = "set")
    private DefaultRedisScript<Boolean> redisScriptSet;

    @Resource(name = "del")
    private DefaultRedisScript<Boolean> redisScriptDel;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/lua-set")
    public String luaSet(){
        List<String> keys = Arrays.asList("testLua", "hello lua");

        Boolean execute = stringRedisTemplate.execute(redisScriptSet, keys, "100");
        assert execute != null;
        return "ok";
    }

    @GetMapping("/lua-del")
    public String luaDel(){
        List<String> keys = Arrays.asList("testLua");

        Boolean execute = stringRedisTemplate.execute(redisScriptDel, keys, "hello lua");
        assert execute != null;
        return "ok";
    }
}
