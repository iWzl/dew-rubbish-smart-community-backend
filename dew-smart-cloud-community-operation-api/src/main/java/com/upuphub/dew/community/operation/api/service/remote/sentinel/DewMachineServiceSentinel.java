package com.upuphub.dew.community.operation.api.service.remote.sentinel;

import com.upuphub.dew.community.connection.constant.MachineConst;
import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.machine.MachineRegisterRequest;
import com.upuphub.dew.community.operation.api.service.remote.DewMachineService;
import org.springframework.stereotype.Component;

@Component
public class DewMachineServiceSentinel implements DewMachineService {
    @Override
    public RpcResultCode registerNewMachineInfo(MachineRegisterRequest machineRegisterRequest) {
        return RpcResultCode.newBuilder().setCode(MachineConst.ERROR_CODE_COMMON_FAIL).build();
    }
}
