package com.upuphub.dew.community.machine.api.service.impl;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.constant.MachineConst;
import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.machine.MachineHealthRequest;
import com.upuphub.dew.community.connection.protobuf.machine.MachineMacAddressRequest;
import com.upuphub.dew.community.connection.protobuf.machine.MachineSimpleInfoResult;
import com.upuphub.dew.community.machine.api.bean.vo.req.MachineHealthReq;
import com.upuphub.dew.community.machine.api.service.MachineService;
import com.upuphub.dew.community.machine.api.service.remote.DewMachineService;
import com.upuphub.dew.community.machine.api.utils.EDSUtil;
import com.upuphub.dew.community.machine.api.utils.HttpUtil;
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
        MachineHealthRequest machineHealthProtoReq = (MachineHealthRequest) MessageUtil.buildMessageByBean(
                MachineHealthRequest.getDescriptor(),MachineHealthRequest.newBuilder(),machineHealthReq);
        MachineHealthRequest machineHealthRequest =
                MachineHealthRequest.newBuilder(machineHealthProtoReq)
                        .setMacAddress(HttpUtil.getMachineMacAddr())
                        .setIpAddr(HttpUtil.getIpAddr()).build();
        RpcResultCode rpcResultCode = dewMachineService.refreshMachineHealthInfo(machineHealthRequest);
        if(null != rpcResultCode){
            return rpcResultCode.getCode();
        }
        return -1;
    }

    @Override
    public boolean checkHardwareMacAddress(String macAddress) {
        MachineSimpleInfoResult machineSimpleInfoResult
                = dewMachineService.fetchSimpleMachineInfoByMacAddress(MachineMacAddressRequest.newBuilder().setMacAddress(macAddress).build());
        if (null != machineSimpleInfoResult && macAddress.equals(machineSimpleInfoResult.getMachineMacAddress())) {
            if(machineSimpleInfoResult.getBindUin() == 0L){
                log.warn("IoTDA Not Bind User MAC[{}] NAME[{}]",macAddress,machineSimpleInfoResult.getMachineName());
            }
            return true;
        } else {
            return false;
        }

    }
}
