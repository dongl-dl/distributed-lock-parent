package com.dongl.servicemeeting.service.impl;

import com.dongl.servicemeeting.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoomServiceImpl implements RoomService {

//    @Autowired
//    private TblOrderDao mapper;


    @Override
    public boolean grab(int userId, int roomId) {
//        TblOrder order = mapper.selectByPrimaryKey(orderId);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // TODO 处理会议室相关的业务逻辑

        if (true/*order.getOrderStatus().intValue() == 0*/) {
//            order.setOrderStatus(1);
//            mapper.updateByPrimaryKeySelective(order);

            return true;
        }
        return false;
    }
}
