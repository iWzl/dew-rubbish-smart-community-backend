package com.upuphub.dew.community.machine.api.controller;

import com.upuphub.dew.community.machine.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.machine.api.bean.vo.req.MachineHealthReq;
import com.upuphub.dew.community.machine.api.service.MachineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/12 10:36
 */

@RestController
@RequestMapping(value = "/api/IoTDA",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "IoTDA",tags = "IoTDA支持模块")
public class MachineController {

    @Autowired
    MachineService machineService;

    @ApiOperation(value = "设备健康属性信息")
    @ApiParam(name = "提交设备属性信息",required = true,value = "提交设备属性信息")
    @PostMapping(value = "/health",consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage hardwareHealthMonitoring(@RequestBody @Validated MachineHealthReq machineHealthReq){
        return ServiceResponseMessage.createBySuccessCodeMessage(
                machineService.hardwareHealthMonitoring(machineHealthReq)
        );
    }
}
