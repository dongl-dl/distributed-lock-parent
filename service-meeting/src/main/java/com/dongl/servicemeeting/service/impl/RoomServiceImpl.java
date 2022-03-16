package com.dongl.servicemeeting.service.impl;

import com.dongl.servicemeeting.domian.RoomInfo;
import com.dongl.servicemeeting.mapper.RoomInfoMapper;
import com.dongl.servicemeeting.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomInfoMapper roomInfoMapper;


    @Override
    public boolean grab(int userId, int roomId) {
        RoomInfo roomInfo = roomInfoMapper.selectByPrimaryKey(roomId);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // TODO 处理会议室相关的业务逻辑

        if (roomInfo.getRoomStatus().intValue() == 0) {
            roomInfo.setRoomStatus(1);
            roomInfoMapper.updateByPrimaryKeySelective(roomInfo);
            return true;
        }
        return false;
    }
}
