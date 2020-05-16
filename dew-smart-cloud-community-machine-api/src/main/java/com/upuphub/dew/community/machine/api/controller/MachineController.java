package com.upuphub.dew.community.machine.api.controller;

import com.upuphub.dew.community.machine.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.machine.api.bean.vo.req.MachineHealthReq;
import com.upuphub.dew.community.machine.api.service.GarbageSearchService;
import com.upuphub.dew.community.machine.api.service.MachineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.IOException;

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

    @Autowired
    GarbageSearchService garbageSearchService;

    @ApiOperation(value = "设备健康属性信息")
    @ApiParam(name = "提交设备属性信息",required = true,value = "提交设备属性信息")
    @PostMapping(value = "/health",consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage hardwareHealthMonitoring(@RequestBody @Validated MachineHealthReq machineHealthReq){
        return ServiceResponseMessage.createBySuccessCodeMessage(
                machineService.hardwareHealthMonitoring(machineHealthReq)
        );
    }

    @ApiOperation(value = "查询指定Key的垃圾分类信息")
    @GetMapping(value = "/tools/{searchKey}/search",consumes = MediaType.ALL_VALUE)
    public ServiceResponseMessage searchGarbageClassByKey(@PathVariable @NotBlank String searchKey){
        return garbageSearchService.searchGarbageClassByKey(searchKey);
    }

    @ApiOperation(value = "查询指定分类的垃圾详细信息")
    @GetMapping(value = "/tools/{classNum}/categories",consumes = MediaType.ALL_VALUE)
    public ServiceResponseMessage searchGarbageCategoriesByNum(@PathVariable @Min(1) @Max(4) Integer classNum){
        return garbageSearchService.searchGarbageCategoriesByNum(classNum);
    }
}
