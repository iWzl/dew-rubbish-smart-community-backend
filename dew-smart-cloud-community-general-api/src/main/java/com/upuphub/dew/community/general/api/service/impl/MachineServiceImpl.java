package com.upuphub.dew.community.general.api.service.impl;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.constant.MachineConst;
import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.machine.*;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.bean.vo.resp.MachineHealthResp;
import com.upuphub.dew.community.general.api.bean.vo.resp.MachineSearchHistoryResp;
import com.upuphub.dew.community.general.api.service.MachineService;
import com.upuphub.dew.community.general.api.service.remote.DewMachineService;
import com.upuphub.dew.community.general.api.utils.EDSUtil;
import com.upuphub.dew.community.general.api.utils.HttpUtil;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {

    @Resource
    DewMachineService dewMachineService;


    @Override
    public ServiceResponseMessage bindHardwareDevices(@NotBlank String macAddress, @NotBlank String bindKey, @NotBlank String nikeName) {
        if("".equals(macAddress) || "".equals(bindKey) || "".equals(nikeName)){
            return ServiceResponseMessage.createByFailCodeMessage("输入的值无效");
        }
        MachineBindInfoRequest machineBindInfoRequest = MachineBindInfoRequest.newBuilder()
                .setBindUin(HttpUtil.getUserUin())
                .setBindKey(bindKey)
                .setMachineName(nikeName)
                .setMachineMacAddress(macAddress)
                .build();
        RpcResultCode  resultCode = dewMachineService.bindHardwareDevices(machineBindInfoRequest);
        if(resultCode.getCode() == MachineConst.ERROR_CODE_NOT_EXISTS){
            return ServiceResponseMessage.createByFailCodeMessage("设备MAC未注册或存在");
        }
        if(resultCode.getCode() == MachineConst.ERROR_CODE_ALREADY_EXISTS){
            return ServiceResponseMessage.createByFailCodeMessage("设备MAC已绑定");
        }
        if(resultCode.getCode() == MachineConst.ERROR_CODE_SUCCESS){
            return ServiceResponseMessage.createBySuccessCodeMessage("绑定成功");
        }
        return ServiceResponseMessage.createByFailCodeMessage("参数错误或者绑定Key无效");
    }

    @Override
    public ServiceResponseMessage fetchMachineHealthInfo() {
        Long uin = HttpUtil.getUserUin();
        MachinesHealthResult machinesHealthResult = dewMachineService.fetchMachineInfoAndHealthByUin(MachineUinRequest.newBuilder().setUin(uin).build());
        if(null == machinesHealthResult || machinesHealthResult.getMachinesHealthResultCount() == 0){
            return ServiceResponseMessage.createBySuccessCodeMessage("未绑定设备");
        }
        MachineHealthResp machineHealthResp = new MachineHealthResp();
        List<MachineHealthResp.MachineHeathInfo> machineHeathInfoList = new ArrayList<>(machinesHealthResult.getMachinesHealthResultCount());
        for (MachineHealthResult machineHealthResult : machinesHealthResult.getMachinesHealthResultList()) {
            MachineHealthResp.MachineHeathInfo machineHeathInfo = MessageUtil.messageToCommonPojo(machineHealthResult,MachineHealthResp.MachineHeathInfo.class);
            if(null != machineHeathInfo){
                MachineHealthResp.MachineHeathInfo.HeathInfo heathInfo =  MessageUtil.messageToCommonPojo(machineHealthResult.getHealthInfoResult(),MachineHealthResp.MachineHeathInfo.HeathInfo.class);
                machineHeathInfo.setHealthInfoResult(heathInfo);
            }
            machineHeathInfoList.add(machineHeathInfo);
        }
        machineHealthResp.setMachineHeathInfoList(machineHeathInfoList);
        return ServiceResponseMessage.createBySuccessCodeMessage("获取成功",machineHealthResp);
    }

    @Override
    public ServiceResponseMessage fetchMachineSearchHistoryInfo(Long startTime, Long endTime, String macAddress) {
        MachineSearchHistoryRequest machineSearchHistoryRequest = EDSUtil.toProtobufMessage(startTime,endTime,macAddress);
        MachinesSearchHistoryResult machinesSearchHistory = dewMachineService.fetchMachineSearchHistoryByUin(machineSearchHistoryRequest);
        if(null == machinesSearchHistory || machinesSearchHistory.getMachineSearchHistoryResultListCount() == 0){
            return ServiceResponseMessage.createBySuccessCodeMessage("没有查询指定的属性信息");
        }
        MachineSearchHistoryResp machineSearchHistoryResp = new MachineSearchHistoryResp();
        List<MachineSearchHistoryResp.History> machineSearchHistoryList = new ArrayList<>(machinesSearchHistory.getMachineSearchHistoryResultListCount());
        for (MachineSearchHistoryResult machineSearchHistoryResult : machinesSearchHistory.getMachineSearchHistoryResultListList()) {
            MachineSearchHistoryResp.History searchHistory = MessageUtil.messageToCommonPojo(machineSearchHistoryResult, MachineSearchHistoryResp.History.class);
            if(null == searchHistory){
                continue;
            }
            List<MachineSearchHistoryResp.History.SearchCount> searchCountList = new ArrayList<>(machineSearchHistoryResult.getMachineSearchCountResultListCount());
            for (MachineSearchCountResult machineSearchCountResult : machineSearchHistoryResult.getMachineSearchCountResultListList()) {
                MachineSearchHistoryResp.History.SearchCount searchCount = MessageUtil.messageToCommonPojo(machineSearchCountResult,
                        MachineSearchHistoryResp.History.SearchCount.class);
                searchCountList.add(searchCount);
            }
            searchHistory.setSearchCountList(searchCountList);
            machineSearchHistoryList.add(searchHistory);
        }
        machineSearchHistoryResp.setMachineSearchHistoryList(machineSearchHistoryList);
        return ServiceResponseMessage.createBySuccessCodeMessage(machineSearchHistoryResp);
    }
}
