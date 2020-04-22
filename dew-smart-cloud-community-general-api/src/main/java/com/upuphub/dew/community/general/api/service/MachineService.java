package com.upuphub.dew.community.general.api.service;

import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;

import javax.validation.constraints.NotBlank;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:55
 */
public interface MachineService {

    /**
     * 将用户和硬件设备绑定
     *
     *
     * @param address
     * @param macAddress 硬件设备的Mac地址
     * @param bindKey 硬件设备的bindKey
     * @return 绑定硬件设备的处理结果
     */
    ServiceResponseMessage bindHardwareDevices(@NotBlank String macAddress,@NotBlank String bindKey, @NotBlank String nikeName);
}
