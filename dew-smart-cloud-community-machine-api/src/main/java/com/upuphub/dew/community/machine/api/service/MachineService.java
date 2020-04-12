package com.upuphub.dew.community.machine.api.service;

import com.upuphub.dew.community.machine.api.bean.vo.req.MachineHealthReq;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/12 13:17
 */
public interface MachineService {
    /**
     * 监控设备健康状态请求
     *
     * @param machineHealthReq 设备健康状态请求
     * @return 处理结果
     */
    int hardwareHealthMonitoring(MachineHealthReq machineHealthReq);

    /**
     * 判断设备的硬件Mac地址
     *
     * @param macAddress mac地址请求
     * @return 判断的结果
     */
    boolean checkHardwareMacAddress(String macAddress);
}
