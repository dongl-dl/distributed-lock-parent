package com.dongl.servicemeeting.constant;

/**
 * @author dongliang7
 * @projectName distributed-lock-parent
 * @ClassName RedisKeyConstant.java
 * @description: redis锁前缀 常量类
 * @createTime 2022年03月19日 23:15:00
 */

public class RedisKeyConstant {

    /**
     * key前缀
     */
    public static final String GRAB_LOCK_ROOM_KEY_PRE = "lock_";

    /**
     * 前缀
     */
    public static final String USER_LISTEN_ROOM_PRE = "user_room_list_";
}