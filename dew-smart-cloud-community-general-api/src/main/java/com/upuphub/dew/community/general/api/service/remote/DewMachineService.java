package com.upuphub.dew.community.general.api.service.remote;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.machine.MachineBindInfoRequest;
import com.upuphub.dew.community.connection.protobuf.machine.MachineUinRequest;
import com.upuphub.dew.community.connection.protobuf.machine.MachinesHealthResult;
import com.upuphub.dew.community.general.api.config.ProtoFeignConfiguration;
import com.upuphub.dew.community.general.api.service.remote.sentinel.DewMachineServiceSentinel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "dew-smart-community-machine",configuration = ProtoFeignConfiguration.class,fallback = DewMachineServiceSentinel.class)
public interface DewMachineService {
    /**
     * 绑定用户设备设备信息
     *
     * @param machineBindInfoRequest 机器绑定相关的请求属性
     * @return 绑定处理的返回结果
     */
    @PostMapping(value = "/IoTDA/bind",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    RpcResultCode bindHardwareDevices(@RequestBody MachineBindInfoRequest machineBindInfoRequest);


    /**
     * 查询用户所有设备的健康信息
     *
     * @param machineUinRequest 用户查询需要的UIN信息
     * @return 处理完成结果
     */
    @PostMapping(value = "/IoTDA/health/search",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    MachinesHealthResult fetchMachineInfoAndHealthByUin(@RequestBody MachineUinRequest machineUinRequest);
}
