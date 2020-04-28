package com.upuphub.dew.community.machine.api.service.impl;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.machine.MachineHealthRequest;
import com.upuphub.dew.community.connection.protobuf.machine.MachineMacAddressRequest;
import com.upuphub.dew.community.connection.protobuf.machine.MachineSearchJournalRequest;
import com.upuphub.dew.community.connection.protobuf.machine.MachineSimpleInfoResult;
import com.upuphub.dew.community.connection.protobuf.push.SyncMachineHealth;
import com.upuphub.dew.community.connection.protobuf.push.SyncMachineSearchInfo;
import com.upuphub.dew.community.machine.api.bean.vo.req.MachineHealthReq;
import com.upuphub.dew.community.machine.api.service.MachineService;
import com.upuphub.dew.community.machine.api.service.remote.DewMachineService;
import com.upuphub.dew.community.machine.api.service.remote.DewPushService;
import com.upuphub.dew.community.machine.api.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
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
    @Resource
    DewPushService dewPushService;

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
            fireMachineHealth(HttpUtil.getMachineMacAddr());
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

    @Override
    public void asyncTrackMachineSearchHistory(String searchKey, String machineMacAddress) {
        dewMachineService.journalMachineSearchHistory(
                MachineSearchJournalRequest.newBuilder()
                        .setSearchKey(searchKey)
                        .setMacAddress(machineMacAddress)
                        .build());
        MachineSimpleInfoResult machineSimpleInfoResult
                = dewMachineService.fetchSimpleMachineInfoByMacAddress(MachineMacAddressRequest.newBuilder().setMacAddress(machineMacAddress).build());
        if (null != machineSimpleInfoResult && machineSimpleInfoResult.getBindUin() > 10000){
            dewPushService.syncMachineSearch(SyncMachineSearchInfo.newBuilder()
                    .setMachineNikeName(machineSimpleInfoResult.getMachineName())
                    .setSearchKey(searchKey)
                    .setTimestamp(System.currentTimeMillis())
                    .setUin(machineSimpleInfoResult.getBindUin())
                    .build()
            );
        }

    }

    @Async
    public void fireMachineHealth(String macAddress){
        MachineSimpleInfoResult machineSimpleInfoResult
                = dewMachineService.fetchSimpleMachineInfoByMacAddress(MachineMacAddressRequest.newBuilder().setMacAddress(macAddress).build());
        if (null != machineSimpleInfoResult && machineSimpleInfoResult.getBindUin() > 10000){
            dewPushService.syncMachineHealthInfo(SyncMachineHealth.newBuilder()
                    .setUin(machineSimpleInfoResult.getBindUin())
                    .setTimestamp(System.currentTimeMillis())
                    .build());
        }

    }
}
