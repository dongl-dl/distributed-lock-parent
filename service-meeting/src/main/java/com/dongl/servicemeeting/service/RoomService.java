package com.dongl.servicemeeting.service;


/**
 * @author dongliang7
 * @projectName distributed-lock-parent
 * @ClassName RoomService.java
 * @description: 修改会议室状态接口
 * @createTime 2022年03月18日 20:10:00
 */
public interface RoomService {

    /**
     * 修改会议室状态
     * @param userId 用户id
     * @param roomId 会议室id
     * @return
     */
    boolean grab(int userId, int roomId);
}
