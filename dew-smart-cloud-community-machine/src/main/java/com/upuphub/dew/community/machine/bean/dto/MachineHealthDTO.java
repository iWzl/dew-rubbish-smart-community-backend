package com.upuphub.dew.community.machine.bean.dto;

import com.upuphub.dew.community.connection.annotation.ProtobufField;
import lombok.Data;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/12 18:11
 */
@Data
public class MachineHealthDTO {
    @ProtobufField
    private Double cpuTemperature;
    @ProtobufField
    private Double cpuUsageRate;
    @ProtobufField
    private Integer memorySize;
    @ProtobufField
    private Integer usedMemorySize;
    @ProtobufField
    private Integer freeMemorySize;
    @ProtobufField
    private String hardDiskSize;
    @ProtobufField
    private String usedHardDiskSize;
    @ProtobufField
    private String freeDiskSize;
    @ProtobufField
    private String diskUseRate;
    @ProtobufField
    private Integer cpuCoreCount;
    @ProtobufField
    private String systemName;
    @ProtobufField
    private String macAddress;
    @ProtobufField
    private String ipAddr;
}
