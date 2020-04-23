package com.upuphub.dew.community.machine.bean.dto;

import com.upuphub.dew.community.connection.annotation.ProtobufField;
import lombok.Data;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/23 22:29
 */

@Data
public class MachineHistorySearchDTO {
    @ProtobufField
    private Long uin;
    @ProtobufField
    private Long startTime;
    @ProtobufField
    private Long endTime;
    @ProtobufField
    private String machineMacAddress;
}
