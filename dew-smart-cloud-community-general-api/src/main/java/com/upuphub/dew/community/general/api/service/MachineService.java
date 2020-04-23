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
     * @param nikeName 设备的别称
     * @param macAddress 硬件设备的Mac地址
     * @param bindKey 硬件设备的bindKey
     * @return 绑定硬件设备的处理结果
     */
    ServiceResponseMessage bindHardwareDevices(@NotBlank String macAddress,@NotBlank String bindKey, @NotBlank String nikeName);

    /**
     * 查询设备的健康状态
     *
     * @return 设备健康状态的查询结果
     */
    ServiceResponseMessage fetchMachineHealthInfo();

    /**
     * 查询指定范围的设备检索信息
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param macAddress 设备的MAC弟子
     * @return 检索完成的处理结果
     */
    ServiceResponseMessage fetchMachineSearchHistoryInfo(Long startTime, Long endTime, String macAddress);
}
