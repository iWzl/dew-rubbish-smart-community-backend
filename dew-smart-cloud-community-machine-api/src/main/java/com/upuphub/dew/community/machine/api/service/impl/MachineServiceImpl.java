package com.upuphub.dew.community.machine.api.service.impl;

import com.upuphub.dew.community.connection.protobuf.machine.MachineMacAddressRequest;
import com.upuphub.dew.community.connection.protobuf.machine.MachineSimpleInfoResult;
import com.upuphub.dew.community.machine.api.bean.vo.req.MachineHealthReq;
import com.upuphub.dew.community.machine.api.service.MachineService;
import com.upuphub.dew.community.machine.api.service.remote.DewMachineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/12 13:18
 */

@Slf4j
@Service
public class MachineServiceImpl implements MachineService {

    @Resource
    DewMachineService dewMachineService;

    @Override
    public int hardwareHealthMonitoring(MachineHealthReq machineHealthReq) {

        return 0;
    }

    @Override
    public boolean checkHardwareMacAddress(String macAddress) {
        MachineSimpleInfoResult machineSimpleInfoResult
                = dewMachineService.fetchSimpleMachineInfoByMacAddress(MachineMacAddressRequest.newBuilder().setMacAddress(macAddress).build());
        if (null != machineSimpleInfoResult && macAddress.equals(machineSimpleInfoResult.getMachineMacAddress())) {
            if(machineSimpleInfoResult.getBindUin() == 0L){
                log.warn("IoTDA Not Bind User Name[{}] Mac[{}]",macAddress,machineSimpleInfoResult.getMachineName());
            }
            return true;
        } else {
            return false;
        }

    }
}
