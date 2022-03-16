package com.dongl.servicemeeting.domian;

import java.io.Serializable;
import lombok.Data;

/**
 * room_info_lock
 * @author 
 */
@Data
public class RoomInfoLock implements Serializable {
    /**
     * 会议室id
     */
    private Integer roomId;

    /**
     * 用户id
     */
    private Integer userId;

    private static final long serialVersionUID = 1L;
}