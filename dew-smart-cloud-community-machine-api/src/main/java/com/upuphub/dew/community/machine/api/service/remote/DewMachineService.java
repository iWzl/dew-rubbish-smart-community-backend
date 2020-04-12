package com.upuphub.dew.community.machine.api.service.remote;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.machine.MachineHealthRequest;
import com.upuphub.dew.community.connection.protobuf.machine.MachineMacAddressRequest;
import com.upuphub.dew.community.connection.protobuf.machine.MachineSimpleInfoResult;
import com.upuphub.dew.community.machine.api.config.ProtoFeignConfiguration;
import com.upuphub.dew.community.machine.api.service.remote.sentinel.DewMachineServiceSentinel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/12 14:54
 */

@FeignClient(value = "dew-smart-community-machine",configuration = ProtoFeignConfiguration.class,fallback = DewMachineServiceSentinel.class)
public interface DewMachineService {
    /**
     * 通过Mac地址 查询设备注册相关信息
     *
     * @param machineMacAddressRequest 设备Mac地址相关请求
     * @return 设备的部分基础信息
     */
    @PostMapping("/IoTDA/simpleInfo")
    MachineSimpleInfoResult fetchSimpleMachineInfoByMacAddress(@RequestBody MachineMacAddressRequest machineMacAddressRequest);

    /**
     * 上报刷新机器的设备相关信息
     *
     * @param machineHealthRequest 机器的健康请求信息
     * @return 机器的监考请求保存处理返回
     */
    @PostMapping("/IoTDA/health")
    RpcResultCode refreshMachineHealthInfo(@RequestBody MachineHealthRequest machineHealthRequest);

}
