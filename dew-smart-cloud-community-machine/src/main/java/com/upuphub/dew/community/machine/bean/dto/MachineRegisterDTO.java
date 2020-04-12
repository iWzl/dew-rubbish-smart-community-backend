package com.upuphub.dew.community.machine.bean.dto;

import com.upuphub.dew.community.connection.annotation.ProtobufField;
import lombok.Data;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/11 22:34
 */

@Data
public class MachineRegisterDTO {
    @ProtobufField
    private String machineMacAddress;
    @ProtobufField
    private String machineName;
    @ProtobufField
    private Integer machineType;
    @ProtobufField
    private String machineVersion;
    @ProtobufField
    private String machineMaker;
    @ProtobufField
    private String bindKey;

}
