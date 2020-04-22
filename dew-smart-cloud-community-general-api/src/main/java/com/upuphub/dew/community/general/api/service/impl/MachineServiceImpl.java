package com.upuphub.dew.community.general.api.service.impl;

import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.service.MachineService;
import com.upuphub.dew.community.general.api.utils.basic.ResultCodeEnum;
import org.springframework.stereotype.Service;

@Service
public class MachineServiceImpl implements MachineService {
    @Override
    public ServiceResponseMessage bindHardwareDevices(String macAddress, String bindKey) {
        if("".equals(macAddress) || "".equals(bindKey)){
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.BAD_REQUEST.getCode(),"输入的值无效");
        }

        return null;
    }
}
