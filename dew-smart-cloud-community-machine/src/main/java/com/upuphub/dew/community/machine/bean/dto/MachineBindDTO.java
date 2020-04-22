package com.upuphub.dew.community.machine.bean.dto;

import com.upuphub.dew.community.connection.annotation.ProtobufField;
import lombok.Data;

@Data
public class MachineBindDTO {
    @ProtobufField
    private String machineMacAddress;
    @ProtobufField
    private String  machineName;
    @ProtobufField
    private String  bindKey;
    @ProtobufField
    private Long  bindUin;
}
