package com.upuphub.dew.community.operation.api.service.impl;

import com.upuphub.dew.community.connection.constant.MachineConst;
import com.upuphub.dew.community.connection.protobuf.machine.MachineRegisterRequest;
import com.upuphub.dew.community.operation.api.bean.vo.req.MachineRegisterReq;
import com.upuphub.dew.community.operation.api.bean.vo.resp.MachineRegisterResp;
import com.upuphub.dew.community.operation.api.service.MachineService;
import com.upuphub.dew.community.operation.api.service.remote.DewMachineService;
import com.upuphub.dew.community.operation.api.utils.EDSUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/12 10:39
 */

@Service
public class MachineServiceImpl implements MachineService {
    @Resource
    DewMachineService dewMachineService;

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
}
