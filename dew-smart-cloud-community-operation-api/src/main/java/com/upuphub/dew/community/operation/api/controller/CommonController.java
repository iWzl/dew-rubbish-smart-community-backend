package com.upuphub.dew.community.operation.api.controller;

import com.upuphub.dew.community.operation.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.operation.api.bean.vo.req.QiNiuTokenReq;
import com.upuphub.dew.community.operation.api.service.QiNiuCloudService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 辅助通用功能的前端控制器
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/6 21:27
 */
@RestController
@RequestMapping(value = "/api/common",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "辅助功能",tags = "辅助功能模块")
public class CommonController {

    @Autowired
    QiNiuCloudService qiNiuCloudService;

    @ApiOperation(value = "获取图片上传凭证")
    @ApiParam(name = "usernameAndPassword",required = true,value = "用户登录/注册信息，同时包含用户的设备信息")
    @PostMapping(value = "/image/token",consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage qiNiuCloudImageToken(@RequestBody @Validated QiNiuTokenReq qiNiuTokenReq){
        return ServiceResponseMessage.createBySuccessCodeMessage(
               qiNiuCloudService.buildQiNiuImageToken(qiNiuTokenReq.getBucketName(),qiNiuTokenReq.getFileKeys())
        );
    }
}
