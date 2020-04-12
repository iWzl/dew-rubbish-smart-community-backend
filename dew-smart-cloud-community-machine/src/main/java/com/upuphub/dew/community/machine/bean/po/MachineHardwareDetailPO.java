package com.upuphub.dew.community.machine.bean.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "machine_hardware_repository")
public class MachineHardwareDetailPO {

    @Id
    private String machineMacAddress;

    @Indexed
    @Field("bind_uin")
    private Long bindUin;

    @Field("bindKey")
    private String bindKey;

    @Field("machine_name")
    private String machineName;

    @Field("machine_type")
    private Integer machineType;

    @Field("machine_version")
    private String machineVersion;

    @Field("machine_maker")
    private String machineMaker;

    @Field("register_time")
    private Long registerTime;

    @Field("bind_time")
    private Long bindTime;

    @Field("machine_nike_name")
    private String nikeName;
}
