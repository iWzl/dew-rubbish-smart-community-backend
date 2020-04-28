package com.upuphub.dew.community.machine.api.service;

import com.upuphub.dew.community.machine.api.bean.vo.req.MachineHealthReq;
import org.springframework.scheduling.annotation.Async;

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

    /**
     * 异步追踪机器分类查询记录
     *
     * @param searchKey 查询的Key
     * @param machineMacAddress 机器的MAC地址
     */
    @Async
    void asyncTrackMachineSearchHistory(String searchKey,String machineMacAddress);

    void fireMachineHealth(String macAddress);
}
