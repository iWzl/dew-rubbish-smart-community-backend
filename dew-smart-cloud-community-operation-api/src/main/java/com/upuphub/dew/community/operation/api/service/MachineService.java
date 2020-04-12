package com.upuphub.dew.community.operation.api.service;

import com.upuphub.dew.community.operation.api.bean.vo.req.MachineRegisterReq;
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
}
