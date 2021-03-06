package com.upuphub.dew.community.machine.controller;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.protobuf.account.Uin;
import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.machine.*;
import com.upuphub.dew.community.machine.bean.dto.MachineBindDTO;
import com.upuphub.dew.community.machine.bean.dto.MachineHealthDTO;
import com.upuphub.dew.community.machine.bean.dto.MachineHistorySearchDTO;
import com.upuphub.dew.community.machine.bean.dto.MachineRegisterDTO;
import com.upuphub.dew.community.machine.service.MachineService;
import com.upuphub.dew.community.machine.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(consumes = "application/x-protobuf", produces = "application/x-protobuf")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @PostMapping("/IoTDA/register")
    public RpcResultCode registerNewMachineInfo(@RequestBody MachineRegisterRequest machineRegisterRequest) {
        MachineRegisterDTO machineRegisterInfo = MessageUtil.messageToCommonPojo(machineRegisterRequest,MachineRegisterDTO.class);
        return RpcResultCode.newBuilder().setCode(
                machineService.registerNewMachine(machineRegisterInfo)
        ).build();
    }

    @PostMapping("/IoTDA/simpleInfo")
    public MachineSimpleInfoResult fetchSimpleMachineInfoByMacAddress(@RequestBody MachineMacAddressRequest machineMacAddressRequest) {
        String macAddress = machineMacAddressRequest.getMacAddress();
        if(ObjectUtil.isEmpty(macAddress)){
            return MachineSimpleInfoResult.newBuilder().build();
        }
        return machineService.fetchSimpleMachineInfoByMacAddress(macAddress);
    }

    @PostMapping("/IoTDA/health")
    public RpcResultCode refreshMachineHealthInfo(@RequestBody MachineHealthRequest machineHealthRequest) {
        MachineHealthDTO machineHealthInfo = MessageUtil.messageToCommonPojo(machineHealthRequest,MachineHealthDTO.class);
        return RpcResultCode.newBuilder().setCode(
                machineService.refreshMachineHealthInfo(machineHealthInfo)
        ).build();
    }

    @PostMapping("/IoTDA/search/journal")
    public RpcResultCode journalMachineSearchHistory(@RequestBody MachineSearchJournalRequest machineSearchJournalRequest) {
        return RpcResultCode.newBuilder().setCode(
                machineService.journalMachineSearchHistory(machineSearchJournalRequest.getMacAddress(),machineSearchJournalRequest.getSearchKey())
        ).build();
    }

    @PostMapping("/IoTDA/bind")
    public RpcResultCode bindHardwareDevices(@RequestBody MachineBindInfoRequest machineBindInfoRequest) {
        MachineBindDTO machineBindInfo = MessageUtil.messageToCommonPojo(machineBindInfoRequest,MachineBindDTO.class);
        return RpcResultCode.newBuilder().setCode(
                machineService.bindHardwareDevices(machineBindInfo)
        ).build();
    }


    @PostMapping("/IoTDA/health/search")
    public MachinesHealthResult fetchMachineInfoAndHealthByUin(@RequestBody MachineUinRequest machineUinRequest) {
        Long uin = machineUinRequest.getUin();
        return MachinesHealthResult.newBuilder().addAllMachinesHealthResult(
                machineService.fetchMachineInfoAndHealthByUin(uin)
        ).build();
    }

    @PostMapping("/IoTDA/search/history")
    public MachinesSearchHistoryResult fetchMachineSearchHistoryByUin(@RequestBody MachineSearchHistoryRequest machineSearchHistoryRequest) {
        MachineHistorySearchDTO machineHistorySearch = MessageUtil.messageToCommonPojo(machineSearchHistoryRequest,MachineHistorySearchDTO.class);
        return MachinesSearchHistoryResult.newBuilder().addAllMachineSearchHistoryResultList(
                machineService.fetchMachineSearchHistoryByUin(machineHistorySearch)
        ).build();
    }


    @PostMapping("/IoTDA/machine/details")
    public MachineRegisterDetailsResult fetchMachineDetailsByDateRange(@RequestBody MachineRegisterDetailsRequest machineRegisterDetailsRequest) {
        return MachineRegisterDetailsResult.newBuilder().addAllMachineRegisterDetails(
                machineService.fetchMachineDetailsByDateRange(
                        machineRegisterDetailsRequest.getStartTime(),machineRegisterDetailsRequest.getEndTime()
                )
        ).build();
    }
}
