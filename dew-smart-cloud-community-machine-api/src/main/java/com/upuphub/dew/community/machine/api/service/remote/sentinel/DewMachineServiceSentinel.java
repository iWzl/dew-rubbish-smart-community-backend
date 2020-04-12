package com.upuphub.dew.community.machine.api.service.remote.sentinel;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.machine.MachineHealthRequest;
import com.upuphub.dew.community.connection.protobuf.machine.MachineMacAddressRequest;
import com.upuphub.dew.community.connection.protobuf.machine.MachineSearchJournalRequest;
import com.upuphub.dew.community.connection.protobuf.machine.MachineSimpleInfoResult;
import com.upuphub.dew.community.machine.api.service.remote.DewMachineService;
import org.springframework.stereotype.Component;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/12 14:56
 */
@Component
public class DewMachineServiceSentinel implements DewMachineService {
    @Override
    public MachineSimpleInfoResult fetchSimpleMachineInfoByMacAddress(MachineMacAddressRequest machineMacAddressRequest) {
        return null;
    }

    @Override
    public RpcResultCode refreshMachineHealthInfo(MachineHealthRequest machineHealthRequest) {
        return null;
    }

    @Override
    public RpcResultCode journalMachineSearchHistory(MachineSearchJournalRequest machineSearchJournalRequest) {
        return null;
    }
}
