package com.upuphub.dew.community.machine.service;

import com.upuphub.dew.community.connection.protobuf.machine.MachineHealthResult;
import com.upuphub.dew.community.connection.protobuf.machine.MachineSimpleInfoResult;
import com.upuphub.dew.community.connection.protobuf.machine.MachinesHealthResult;
import com.upuphub.dew.community.machine.bean.dto.MachineBindDTO;
import com.upuphub.dew.community.machine.bean.dto.MachineHealthDTO;
import com.upuphub.dew.community.machine.bean.dto.MachineRegisterDTO;

import java.util.List;


/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/11 22:42
 */
public interface MachineService {
    /**
     * 注册新的IOT设备
     *
     * @param machineRegisterInfo 新的IOT设备的基本信息
     * @return 注册的处理结果
     */
    int registerNewMachine(MachineRegisterDTO machineRegisterInfo);

    /**
     * 根据设备Mac地址 查询设备基础上报属性
     *
     * @param macAddress 基础Mac地址
     * @return 设备Mac地址和其他基础属性
     */
    MachineSimpleInfoResult fetchSimpleMachineInfoByMacAddress(String macAddress);

    /**
     * 刷新新的硬件属性信息
     *
     * @param machineHealthInfo 硬件属性信息
     * @return 硬件属性信息的刷新结果
     */
    int refreshMachineHealthInfo(MachineHealthDTO machineHealthInfo);

    /**
     * 记录硬件设备的查询历史记录
     *
     * @param macAddress 硬件设备的IP地址
     * @param searchKey 查询的Key
     * @return 追踪的处理结果
     */
    int journalMachineSearchHistory(String macAddress, String searchKey);

    /**
     * 绑定硬件设备和驱动信息
     *
     * @param machineBindInfo 机器绑定的相关信息
     * @return 绑定设备的处理结果
     */
    int bindHardwareDevices(MachineBindDTO machineBindInfo);

    /**
     * 根据UIN查询指定用户的健康状态信息
     *
     * @param uin 用户UIN
     * @return 设备健康状态的处理结果
     */
    List<MachineHealthResult> fetchMachineInfoAndHealthByUin(Long uin);
}
