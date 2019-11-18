package com.upuphub.dew.community.general.api.controller;

import com.upuphub.dew.community.connection.protobuf.account.Location;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

/**
 * 瞬间动态模块得前端控制器
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/6 21:27
 */
@RestController
@RequestMapping(value = "/api/test",consumes = "application/x-protobuf")
@Api(value = "ProtobufTest",tags = "测试API")
public class ProtobufTestController {

    @ApiOperation(value = "ProtobufTest")
    @ApiParam(name = "inputStream",required = true,value = "ProtobufTest")
    @PostMapping(value = "/protobuf",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage postMomentsDynamicContent(@RequestBody @Validated InputStream inputStream) throws IOException {
        Location location = Location.parseFrom(inputStream);
        System.out.println(location);
        return ServiceResponseMessage.createByFailCodeMessage(location.toString());
    }
}
