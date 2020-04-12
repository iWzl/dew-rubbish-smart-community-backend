package com.upuphub.dew.community.machine.bean.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Data
@Document(collection = "machine_search_history_repository")
public class MachineSearchHistoryPO {
    @Id
    private String key;
    @Field("record_time")
    private String recordTime;
    @Field("record_timestamp")
    private Long recordTimestamp;
    @Field("machine_address")
    private String machineAddress;
    @Field("search_key_and_times")
    private Map<String,Integer> searchKeyAndTimes;
}
