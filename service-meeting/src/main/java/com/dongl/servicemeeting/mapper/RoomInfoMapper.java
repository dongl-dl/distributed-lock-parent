package com.dongl.servicemeeting.mapper;


import com.dongl.servicemeeting.domian.RoomInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoomInfoMapper {
    int deleteByPrimaryKey(Integer roomId);

    int insert(RoomInfo record);

    int insertSelective(RoomInfo record);

    RoomInfo selectByPrimaryKey(@Param("roomId") Integer roomId);

    int updateByPrimaryKeySelective(RoomInfo record);

    int updateByPrimaryKey(RoomInfo record);
}