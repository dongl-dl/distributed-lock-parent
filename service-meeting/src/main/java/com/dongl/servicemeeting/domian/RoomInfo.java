package com.dongl.servicemeeting.domian;

import java.io.Serializable;
import lombok.Data;

/**
 * room_info
 * @author 
 */
@Data
public class RoomInfo implements Serializable {
    /**
     * 会议室id
     */
    private Integer roomId;

    /**
     * 会议室状态
     */
    private Integer roomStatus;

    private static final long serialVersionUID = 1L;
}