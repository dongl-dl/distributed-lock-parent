package com.dongl.servicemeeting.service;


/**
 * @author dongliang7
 * @projectName distributed-lock-parent
 * @ClassName RenewGrabLockService.java
 * @description: 手写redis锁续期接口
 * @createTime 2022年03月18日 20:12:00
 */
public interface RenewGrabLockService {

    /**
     * 续约
     * @param key
     * @param value
     * @param time
     */
    void renewLock(String key , String value , int time);
}
