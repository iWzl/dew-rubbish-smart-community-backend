package com.upuphub.dew.community.operation.api.bean.vo.resp;

import com.upuphub.dew.community.connection.annotation.ProtobufField;
import lombok.Data;

import java.util.List;

@Data
public class MachineCoreDetailsResp {

    List<MachineDetailInfo> machineDetailInfoList;

    @Data
    public static class MachineDetailInfo {
        @ProtobufField
        private String machineMacAddress;
        @ProtobufField
        private String bindKey;
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
        private SimpleProfileResp simpleProfileResp;
        @ProtobufField
        private Long bindTime;
    }
}
