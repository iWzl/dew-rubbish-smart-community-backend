package com.upuphub.dew.community.operation.api.controller;

import com.upuphub.dew.community.operation.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.operation.api.bean.vo.req.LocationReq;
import com.upuphub.dew.community.operation.api.bean.vo.req.NewProfileReq;
import com.upuphub.dew.community.operation.api.bean.vo.req.PasswordReq;
import com.upuphub.dew.community.operation.api.bean.vo.req.UsernameAndPasswordReq;
import com.upuphub.dew.community.operation.api.bean.vo.resp.SimpleProfileResp;
import com.upuphub.dew.community.operation.api.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/11 00:19
 */

@RestController
@RequestMapping(value = "/api/account",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "账户模块",tags = "账户相关模块")
public class AccountController {
    @Autowired
    AccountService accountService;


    @ApiOperation(value = "用户登录/注册接口",consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiParam(name = "usernameAndPassword",required = true,value = "用户登录/注册信息，同时包含用户的设备信息")
    @PostMapping(value = "/login")
    public ServiceResponseMessage loginOrRegister(@RequestBody @Validated UsernameAndPasswordReq usernameAndPassword){
        return accountService.loginOrRegister(usernameAndPassword);
    }

    @ApiOperation(value = "完善注册信息",consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiParam(name = "ProfileModifyReq",required = true,value = "注册用户信息")
    @PostMapping(value = "/profile/new")
    public ServiceResponseMessage createNewProfile(@RequestBody @Validated NewProfileReq profileReq){
        return accountService.improveUserInformation(profileReq);
    }

    @ApiOperation(value = "刷新用户地理位置")
    @PostMapping(value = "/location/refresh",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ServiceResponseMessage refreshingLocation(@RequestBody @Validated LocationReq locationReq){
        return accountService.refreshLocation(locationReq);
    }

    @ApiOperation(value = "用户注销接口")
    @ApiParam(value = "用户注销登录")
    @PostMapping(value = "/logout")
    public ServiceResponseMessage logout(){
        accountService.logout();
        return ServiceResponseMessage.createBySuccessCodeMessage();
    }

    @ApiOperation(value = "根据用户openId获取用户信息",consumes =  MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiParam(name = "uid",required = true,value = "用户UID")
    @GetMapping(value = "/info/{openId}/get")
    public ServiceResponseMessage getSimpleProfileWithUid(@PathVariable("openId") @NotBlank String openId) {
        SimpleProfileResp simpleProfileResp = accountService.pullSimpleProfileByOpenId(openId);
        return ServiceResponseMessage.createBySuccessCodeMessage(simpleProfileResp);
    }

    @ApiOperation(value = "刷新用户Profile信息")
    @GetMapping(value = "/profile/refresh")
    public ServiceResponseMessage refreshUserProfile(@RequestParam(value = "openId",required = false) String openId){
        return accountService.refreshUserProfile(openId);
    }

    @ApiOperation(value = "修改用户信息")
    @ApiParam(name = "ProfileModifyReq",required = true,value = "用户待修改的信息组")
    @PutMapping(value = "/profile/modify")
    public ServiceResponseMessage modifyUserProfile(@RequestBody Map<String,String> modifyMap){
        return accountService.resetUserProfile(modifyMap);
    }
}
