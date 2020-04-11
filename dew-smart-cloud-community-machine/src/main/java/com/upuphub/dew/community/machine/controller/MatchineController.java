package com.upuphub.dew.community.machine.controller;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.machine.MachineRegisterRequest;
import com.upuphub.dew.community.machine.bean.dto.MachineRegisterDTO;
import com.upuphub.dew.community.machine.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(consumes = "application/x-protobuf", produces = "application/x-protobuf")
public class MatchineController {

    @Autowired
    private MachineService machineService;

    @PostMapping("/IoTDA/register")
    public RpcResultCode registerNewMachineInfo(@RequestBody MachineRegisterRequest machineRegisterRequest) {
        MachineRegisterDTO machineRegisterInfo = MessageUtil.messageToCommonPojo(machineRegisterRequest,MachineRegisterDTO.class);
        return RpcResultCode.newBuilder().setCode(
                machineService.registerNewMachine(machineRegisterInfo)
        ).build();
    }
}
