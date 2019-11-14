package com.upuphub.dew.community.general.api.controller;

import com.upuphub.dew.community.general.api.bean.vo.req.MomentDynamicContentReq;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.service.MomentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 瞬间动态模块得前端控制器
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/6 21:27
 */
@RestController
@RequestMapping(value = "/api/moments",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "用户瞬间动态",tags = "瞬间动态模块")
public class MomentsController {
    @Autowired
    MomentsService momentsService;

    @ApiOperation(value = "获取图片上传凭证")
    @ApiParam(name = "usernameAndPassword",required = true,value = "用户登录/注册信息，同时包含用户的设备信息")
    @PostMapping(value = "/image/token",consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage qiNiuCloudImageToken(@RequestBody @Validated MomentDynamicContentReq momentDynamicContent){
        return ServiceResponseMessage.createBySuccessCodeMessage(
                momentsService.postMomentDynamicContent(momentDynamicContent)
        );
    }
}
