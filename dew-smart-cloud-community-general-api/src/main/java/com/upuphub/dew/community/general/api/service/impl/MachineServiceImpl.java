package com.upuphub.dew.community.general.api.service.impl;

import com.upuphub.dew.community.connection.constant.MachineConst;
import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.machine.MachineBindInfoRequest;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.service.MachineService;
import com.upuphub.dew.community.general.api.service.remote.DewMachineService;
import com.upuphub.dew.community.general.api.utils.HttpUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
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
        return null;
    }
}
