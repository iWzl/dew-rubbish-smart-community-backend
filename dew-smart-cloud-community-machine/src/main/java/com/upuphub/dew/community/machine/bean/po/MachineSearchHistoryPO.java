package com.upuphub.dew.community.machine.bean.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Data
@Document(collection = "machine_search_history_repository")
@CompoundIndexes({
        @CompoundIndex(name = "mac_address_and_timestamp", def = "{'record_timestamp': 1, 'machine_address': -1}")
})
public class MachineSearchHistoryPO {
    @Id
    private String key;
    @Field("record_time")
    private String recordTime;
    @Indexed
    @Field("record_timestamp")
    private Long recordTimestamp;
    @Field("machine_address")
    private String machineAddress;
    @Field("search_key_and_times")
    private Map<String,Integer> searchKeyAndTimes;
}
