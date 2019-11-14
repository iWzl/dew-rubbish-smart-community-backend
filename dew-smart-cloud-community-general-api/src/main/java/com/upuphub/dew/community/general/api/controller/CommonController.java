package com.upuphub.dew.community.general.api.controller;

import com.upuphub.dew.community.general.api.bean.vo.req.QiNiuTokenReq;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.service.GarbageSearchService;
import com.upuphub.dew.community.general.api.service.PushService;
import com.upuphub.dew.community.general.api.service.QiNiuCloudService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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
    @Autowired
    PushService pushService;
    @Autowired
    GarbageSearchService garbageSearchService;

    @ApiOperation(value = "获取图片上传凭证")
    @ApiParam(name = "usernameAndPassword",required = true,value = "用户登录/注册信息，同时包含用户的设备信息")
    @PostMapping(value = "/image/token",consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage qiNiuCloudImageToken(@RequestBody @Validated QiNiuTokenReq qiNiuTokenReq){
        return ServiceResponseMessage.createBySuccessCodeMessage(
                qiNiuCloudService.buildQiNiuImageToken(qiNiuTokenReq.getBucketName(),qiNiuTokenReq.getFileKeys())
        );
    }

    @ApiOperation(value = "发送验证邮件")
    @GetMapping(value = "/tools/code/{email}/send",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ServiceResponseMessage sendVerifyCodeWithEmail(@PathVariable @Email String email){
        return pushService.sendEmailVerifyCode(email);
    }

    @ApiOperation(value = "查询指定Key的垃圾分类信息")
    @GetMapping(value = "/tools/{searchKey}/search",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ServiceResponseMessage searchGarbageClassByKey(@PathVariable @NotBlank String searchKey){
        return garbageSearchService.searchGarbageClassByKey(searchKey);
    }

    @ApiOperation(value = "查询指定分类的垃圾详细信息")
    @GetMapping(value = "/tools/{classNum}/categories",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ServiceResponseMessage searchGarbageCategoriesByNum(@PathVariable @Min(1) @Max(4) Integer classNum){
        return garbageSearchService.searchGarbageCategoriesByNum(classNum);
    }
}
