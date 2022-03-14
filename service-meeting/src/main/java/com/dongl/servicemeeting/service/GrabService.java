package com.dongl.servicemeeting.service;

public interface GrabService {

    /**
     * 用户抢会议室
     * @param userId 用户id
     * @param roomId 会议室id
     * @return
     */
    String grabRoom(int userId , int roomId);
}
