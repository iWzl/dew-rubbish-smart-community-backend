package com.upuphub.dew.community.machine.bean.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "machine_health_repository")
public class MachineHealthInfoPO {
    @Id
    private String macAddress;
    @Field("cpu_temperature")
    private Double cpuTemperature;
    @Field("cpu_usage_rate")
    private Double cpuUsageRate;
    @Field("memory_size")
    private Integer memorySize;
    @Field("used_memory_size")
    private Integer usedMemorySize;
    @Field("free_memory_size")
    private Integer freeMemorySize;
    @Field("hard_disk_size")
    private String hardDiskSize;
    @Field("used_hard_disk_size")
    private String usedHardDiskSize;
    @Field("free_disk_size")
    private String freeDiskSize;
    @Field("disk_use_rate")
    private String diskUseRate;
    @Field("cpu_core_count")
    private Integer cpuCoreCount;
    @Field("system_name")
    private String systemName;
    @Field("ip_address")
    private String ipAddr;
    @Field("refresh_time")
    private Long refreshTime;
}
