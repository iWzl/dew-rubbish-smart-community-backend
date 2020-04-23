package com.upuphub.dew.community.general.api.bean.vo.resp;

import com.upuphub.dew.community.connection.annotation.ProtobufField;
import lombok.Data;

import java.util.List;

@Data
public class MachineHealthResp {

    List<MachineHeathInfo> machineHeathInfoList;

    @Data
    public static class MachineHeathInfo{
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
        private String nikeName;
        @ProtobufField(ignore = true)
        private HeathInfo healthInfoResult;


        @Data
        public static class HeathInfo{
            @ProtobufField
            private Integer cpuCoreCount;
            @ProtobufField
            private Double cpuTemperature;
            @ProtobufField
            private Double cpuUsageRate;
            @ProtobufField
            private String diskUseRate;
            @ProtobufField
            private String freeDiskSize;
            @ProtobufField
            private Integer freeMemorySize;
            @ProtobufField
            private String hardDiskSize;
            @ProtobufField
            private Integer memorySize;
            @ProtobufField
            private String systemName;
            @ProtobufField
            private String usedHardDiskSize;
            @ProtobufField
            private Integer usedMemorySize;
            @ProtobufField
            private String macAddress;
            @ProtobufField
            private String ipAddr ;
        }
    }


}
