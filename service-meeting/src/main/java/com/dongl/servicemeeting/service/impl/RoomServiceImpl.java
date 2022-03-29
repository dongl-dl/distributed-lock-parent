package com.dongl.servicemeeting.service.impl;

import com.dongl.servicemeeting.domian.RoomInfo;
import com.dongl.servicemeeting.mapper.RoomInfoMapper;
import com.dongl.servicemeeting.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dongliang7
 * @projectName distributed-lock-parent
 * @ClassName RoomServiceImpl.java
 * @description: 会议室业务层处理
 * @createTime 2022年03月17日 22:52:00
 */
@Service
@Slf4j
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomInfoMapper roomInfoMapper;


    /**
     * 修改会议室状态
     * @param userId 用户id
     * @param roomId 会议室id
     * @return
     */
    @Override
    public boolean grab(int userId, int roomId) {
        RoomInfo roomInfo = roomInfoMapper.selectByPrimaryKey(roomId);

        // TODO 处理会议室相关的业务逻辑
        //模拟业务 睡 200ms
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (roomInfo.getRoomStatus().intValue() == 0) {
            roomInfo.setRoomStatus(1);
            roomInfoMapper.updateByPrimaryKeySelective(roomInfo);
            return true;
        }
        return false;
    }
}
