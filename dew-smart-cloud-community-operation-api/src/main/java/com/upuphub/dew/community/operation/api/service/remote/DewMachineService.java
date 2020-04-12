package com.upuphub.dew.community.operation.api.service.remote;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.machine.MachineRegisterRequest;
import com.upuphub.dew.community.operation.api.config.ProtoFeignConfiguration;
import com.upuphub.dew.community.operation.api.service.remote.sentinel.DewMachineServiceSentinel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/11 00:25
 */

@FeignClient(value = "dew-smart-community-machine",configuration = ProtoFeignConfiguration.class,fallback = DewMachineServiceSentinel.class)
public interface DewMachineService {
    /**
     * 注册新的物联网设备信息
     *
     * @param machineRegisterRequest 物联网设备信息的注册请求
     * @return 注册完成的处理结果
     */
    @PostMapping(value = "/IoTDA/register",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    RpcResultCode registerNewMachineInfo(@RequestBody MachineRegisterRequest machineRegisterRequest);
}