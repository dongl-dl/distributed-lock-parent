package com.dongl.servicemeeting.service;

/**
 * @author dongliang7
 * @projectName distributed-lock-parent
 * @ClassName GrabService.java
 * @description: 抢占会议室接口
 * @createTime 2022年03月18日 20:19:00
 */
public interface GrabService {

    /**
     * 用户抢会议室
     * @param userId 用户id
     * @param roomId 会议室id
     * @return
     */
    String grabRoom(int userId , int roomId);
}
