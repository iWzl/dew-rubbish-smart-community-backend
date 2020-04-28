package com.upuphub.dew.community.machine.api.service.remote.sentinel;


import com.upuphub.dew.community.connection.constant.PushConst;
import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.push.SyncMachineHealth;
import com.upuphub.dew.community.connection.protobuf.push.SyncMachineSearchInfo;
import com.upuphub.dew.community.machine.api.service.remote.DewPushService;
import org.springframework.stereotype.Component;

@Component
public class DewPushServiceSentinel implements DewPushService {
    @Override
    public RpcResultCode syncMachineHealthInfo(SyncMachineHealth syncMachineHealth) {
        return RpcResultCode.newBuilder().setCode(PushConst.ERROR_CODE_SUCCESS).build();
    }

    @Override
    public RpcResultCode syncMachineSearch(SyncMachineSearchInfo syncMachineSearchInfo) {
        return RpcResultCode.newBuilder().setCode(PushConst.ERROR_CODE_SUCCESS).build();
    }
}
