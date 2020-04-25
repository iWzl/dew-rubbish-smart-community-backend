package com.upuphub.dew.community.operation.api.service.impl;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.constant.MachineConst;
import com.upuphub.dew.community.connection.protobuf.machine.MachineRegisterDetail;
import com.upuphub.dew.community.connection.protobuf.machine.MachineRegisterDetailsRequest;
import com.upuphub.dew.community.connection.protobuf.machine.MachineRegisterDetailsResult;
import com.upuphub.dew.community.connection.protobuf.machine.MachineRegisterRequest;
import com.upuphub.dew.community.operation.api.bean.vo.req.MachineRegisterReq;
import com.upuphub.dew.community.operation.api.bean.vo.resp.MachineCoreDetailsResp;
import com.upuphub.dew.community.operation.api.bean.vo.resp.MachineRegisterResp;
import com.upuphub.dew.community.operation.api.service.AccountService;
import com.upuphub.dew.community.operation.api.service.MachineService;
import com.upuphub.dew.community.operation.api.service.remote.DewMachineService;
import com.upuphub.dew.community.operation.api.utils.EDSUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/12 10:39
 */

@Service
public class MachineServiceImpl implements MachineService {
    @Resource
    DewMachineService dewMachineService;
    @Resource
    AccountService accountService;

    @Override
    public MachineRegisterResp registerMachineInfo(MachineRegisterReq machineRegisterReq) {
        MachineRegisterRequest machineRegisterRequest = EDSUtil.toProtobufMessage(machineRegisterReq);
        String bindKey = machineRegisterRequest.getBindKey();
        int error = dewMachineService.registerNewMachineInfo(machineRegisterRequest).getCode();
        if(error == MachineConst.ERROR_CODE_SUCCESS){
            return new MachineRegisterResp(bindKey);
        }
        return new MachineRegisterResp(String.format("Register Error %s",error));
    }

    @Override
    public MachineCoreDetailsResp searchMachineDetailsByDateRange(Long startTime, Long endTime) {
        MachineRegisterDetailsResult machineRegisterDetailsResult =
                dewMachineService.fetchMachineDetailsByDateRange(MachineRegisterDetailsRequest.newBuilder()
                        .setStartTime(startTime).setEndTime(endTime)
                        .build());
        MachineCoreDetailsResp machineCoreDetailsResp = new MachineCoreDetailsResp();
        List<MachineCoreDetailsResp.MachineDetailInfo> machineDetailInfoList = new ArrayList<>();
        if(null != machineRegisterDetailsResult && machineRegisterDetailsResult.getMachineRegisterDetailsCount() > 0){
            for (MachineRegisterDetail machineRegisterDetail : machineRegisterDetailsResult.getMachineRegisterDetailsList()) {
                MachineCoreDetailsResp.MachineDetailInfo machineDetailInfo =
                        MessageUtil.messageToCommonPojo(machineRegisterDetail,MachineCoreDetailsResp.MachineDetailInfo.class);
                if(null != machineDetailInfo){
                    machineDetailInfo.setSimpleProfileResp(accountService.pullSimpleProfileByUin(machineRegisterDetail.getBindUin()));
                }
                machineDetailInfoList.add(machineDetailInfo);
            }
        }
        machineCoreDetailsResp.setMachineDetailInfoList(machineDetailInfoList);
        return machineCoreDetailsResp;
    }
}
