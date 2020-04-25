package com.upuphub.dew.community.operation.api.service;

import com.upuphub.dew.community.operation.api.bean.vo.req.MachineRegisterReq;
import com.upuphub.dew.community.operation.api.bean.vo.resp.MachineCoreDetailsResp;
import com.upuphub.dew.community.operation.api.bean.vo.resp.MachineRegisterResp;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/12 10:39
 */
public interface MachineService {
    /**
     * 注册新的物联网设备
     *
     * @param machineRegisterReq 注册新的物联网设备请求
     * @return 机器注册处理结果
     */
    MachineRegisterResp registerMachineInfo(MachineRegisterReq machineRegisterReq);

    /**
     * 查询机器的信息 按注册时间查询
     *
     * @param startTime 查询注册的开始时间
     * @param endTime 查询注册的结束时间
     * @return 机器信息状态的查询处理结果
     */
    MachineCoreDetailsResp searchMachineDetailsByDateRange(Long startTime, Long endTime);
}
