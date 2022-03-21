package com.dongl.servicemeeting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 * lua脚本
 */
@Configuration
public class LuaConfiguration {

    @Bean(name = "set")
    public DefaultRedisScript<Boolean> redisScript(){
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("luascript/lock-set.lua")));
        redisScript.setResultType(Boolean.class);
        return redisScript;
    }

    @Bean(name = "del")
    public DefaultRedisScript<Boolean> redisScriptDel(){
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("luascript/lock-del.lua")));
        redisScript.setResultType(Boolean.class);
        return redisScript;
    }
}
