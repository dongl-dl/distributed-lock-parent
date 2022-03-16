package com.dongl.servicemeeting.mapper;


import com.dongl.servicemeeting.domian.RoomInfoLock;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomInfoLockMaaper {
    int insert(RoomInfoLock record);

    int insertSelective(RoomInfoLock record);

    int deleteByPrimaryKey(Integer roomId);
}