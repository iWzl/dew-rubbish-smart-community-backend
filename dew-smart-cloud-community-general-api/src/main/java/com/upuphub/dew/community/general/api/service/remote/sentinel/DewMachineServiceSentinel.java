package com.upuphub.dew.community.general.api.service.remote.sentinel;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.machine.MachineBindInfoRequest;
import com.upuphub.dew.community.general.api.exception.RpcServiceConnectionException;
import com.upuphub.dew.community.general.api.service.remote.DewMachineService;
import org.springframework.stereotype.Component;

@Component
public class DewMachineServiceSentinel implements DewMachineService {
    @Override
    public RpcResultCode bindHardwareDevices(MachineBindInfoRequest machineBindInfoRequest) {
        throw new RpcServiceConnectionException("Call Dew Machine Service Error");
    }
}