package com.dongl.servicemeeting.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author dongliang7
 * @projectName distributed-lock-parent
 * @ClassName RedisConfig.java
 * @description: redis锁配置类
 * @createTime 2022年03月20日 22:02:00
 */

@Component
public class RedisConfig {

    @Autowired
    RedisSentinelProperties properties;
    /**------------------------------ redisson锁，哨兵 ----------------------------------*/
//    @Bean(name = "redisson")
//    @Order(1)
//    public Redisson getRedisson(){
//
//        Config config = new Config();
//        config.useSentinelServers()
//                .setMasterName(properties.getMasterName())
//                .addSentinelAddress(properties.getAddress())
//                .setDatabase(0);
//        return (Redisson) Redisson.create(config);
//    }



    /**------------------------------ 红锁 ----------------------------------*/
    @Bean(name = "redissonRed1")
    @Primary
    public RedissonClient redissonRed1(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6380").setDatabase(0).setPassword("CcRe123456@");
        return Redisson.create(config);
    }
    @Bean(name = "redissonRed2")
    public RedissonClient redissonRed2(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6381").setDatabase(0).setPassword("CcRe123456@");
        return Redisson.create(config);
    }
    @Bean(name = "redissonRed3")
    public RedissonClient redissonRed3(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6382").setDatabase(0).setPassword("CcRe123456@");
        return Redisson.create(config);
    }




    /**------------------------------ 单个redis ----------------------------------*/
//    @Bean
//    @ConditionalOnMissingBean(StringRedisTemplate.class)
//    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
//    	StringRedisTemplate redisTemplate = new StringRedisTemplate();
//    	redisTemplate.setConnectionFactory(redisConnectionFactory);
//    	return redisTemplate;
//
//    }
    

    /**------------------------------ 单个redisson ----------------------------------*/
//    @Bean
//    public RedissonClient redissonClient() {
//    	Config config = new Config();
//    	config.useSingleServer().setAddress("redis://127.0.0.1:6383").setDatabase(1).setPassword("CcRe123456@");
//    	return Redisson.create(config);
//    }
}
