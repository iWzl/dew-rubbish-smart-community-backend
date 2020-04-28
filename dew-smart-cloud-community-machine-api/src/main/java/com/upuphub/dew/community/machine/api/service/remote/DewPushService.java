package com.upuphub.dew.community.machine.api.service.remote;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.machine.MachineHealthRequest;
import com.upuphub.dew.community.connection.protobuf.machine.MachineMacAddressRequest;
import com.upuphub.dew.community.connection.protobuf.machine.MachineSearchJournalRequest;
import com.upuphub.dew.community.connection.protobuf.machine.MachineSimpleInfoResult;
import com.upuphub.dew.community.connection.protobuf.push.SyncMachineHealth;
import com.upuphub.dew.community.connection.protobuf.push.SyncMachineSearchInfo;
import com.upuphub.dew.community.machine.api.config.ProtoFeignConfiguration;
import com.upuphub.dew.community.machine.api.service.remote.sentinel.DewMachineServiceSentinel;
import com.upuphub.dew.community.machine.api.service.remote.sentinel.DewPushServiceSentinel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/12 14:54
 */

@FeignClient(value = "dew-smart-community-push",configuration = ProtoFeignConfiguration.class,fallback = DewPushServiceSentinel.class)
public interface DewPushService {

    @PostMapping(value = "/push/machine/health/fire",consumes = "application/x-protobuf", produces = "application/x-protobuf")
    RpcResultCode syncMachineHealthInfo(@RequestBody SyncMachineHealth syncMachineHealth);


    @PostMapping(value = "/push/machine/search/fire",consumes = "application/x-protobuf", produces = "application/x-protobuf")
    RpcResultCode syncMachineSearch(@RequestBody SyncMachineSearchInfo syncMachineSearchInfo);
}
