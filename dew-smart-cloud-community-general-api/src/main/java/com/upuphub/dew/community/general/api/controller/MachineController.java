package com.upuphub.dew.community.general.api.controller;

import com.upuphub.dew.community.connection.protobuf.account.Location;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.bean.vo.req.IoTDABindReq;
import com.upuphub.dew.community.general.api.service.MachineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

/**
 * 硬件设备相关的用户端的前端控制器
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/6 21:27
 */
@RestController
@RequestMapping(value = "/api/IoTDA",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "IoTDA", tags = "IoTDA支持模块")
public class MachineController {
    @Autowired
    MachineService machineService;

    @ApiOperation(value = "绑定硬件设备")
    @ApiParam(name = "iotDABindReq", required = true, value = "iotDABindReq")
    @PostMapping(value = "/bind", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage bindHardwareDevices(@RequestBody @Validated IoTDABindReq iotDABindReq){
        return machineService.bindHardwareDevices(iotDABindReq.getMacAddress(),iotDABindReq.getBindKey());
    }
}
