package com.upuphub.dew.community.general.api.bean.vo.resp;

import com.upuphub.dew.community.connection.annotation.ProtobufField;
import lombok.Data;

import java.util.List;

@Data
public class MachineSearchHistoryResp {

    private List<History> machineSearchHistoryList;


    @Data
    public static class History{
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
        private List<SearchCount> searchCountList;


        @Data
        public static class SearchCount{
            private String searchName;
            private Integer searchCount;
        }
    }
}
