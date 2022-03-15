package com.dongl.servicemeeting.service;


public interface RenewGrabLockService {

    /**
     * 续约
     * @param key
     * @param value
     * @param time
     */
    void renewLock(String key , String value , int time);
}
