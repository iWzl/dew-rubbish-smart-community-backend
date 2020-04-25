package com.upuphub.dew.community.operation.api.controller;

import com.upuphub.dew.community.operation.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.operation.api.bean.vo.req.DateRangeReq;
import com.upuphub.dew.community.operation.api.bean.vo.req.MachineRegisterReq;
import com.upuphub.dew.community.operation.api.service.MachineService;
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

    @ApiOperation(value = "注册新的IoT设备")
    @ApiParam(name = "MachineRegisterReq",required = true,value = "注册新的IOT设备")
    @PostMapping(value = "/register",consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage registerMachineInfo(@RequestBody @Validated MachineRegisterReq machineRegisterReq){
        return ServiceResponseMessage.createBySuccessCodeMessage(
                machineService.registerMachineInfo(machineRegisterReq)
        );
    }

    @ApiOperation(value = "获取指定注册时间范围所有硬件设备信息")
    @ApiParam(name = "DateRangeReq",required = true,value = "时间筛选范围")
    @PostMapping(value = "/detail",consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage searchMachineDetailsByDateRange(@RequestBody @Validated DateRangeReq dateRangeReq){
        return ServiceResponseMessage.createBySuccessCodeMessage(
                machineService.searchMachineDetailsByDateRange(dateRangeReq.getStartTime(),dateRangeReq.getEndTime())
        );
    }

}
