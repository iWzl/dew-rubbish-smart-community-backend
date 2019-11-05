package com.upuphub.dew.community.general.api.service.impl;

import cc.itsc.rbc.api.bean.req.LocationReq;
import cc.itsc.rbc.api.bean.req.NewProfileReq;
import cc.itsc.rbc.api.bean.req.UsernameAndPasswordReq;
import cc.itsc.rbc.api.bean.vo.common.AccountResponseMessage;
import cc.itsc.rbc.api.bean.vo.common.ServiceResponseMessage;
import cc.itsc.rbc.api.bean.vo.resp.*;
import cc.itsc.rbc.api.service.AccountService;
import cc.itsc.rbc.api.service.RedisCacheService;
import cc.itsc.rbc.api.service.rpc.RpcAccountService;
import cc.itsc.rbc.api.shiro.AuthRealm;
import cc.itsc.rbc.api.shiro.JWTUtil;
import cc.itsc.rbc.api.utils.DewOpenIdUtil;
import cc.itsc.rbc.api.utils.EDSUtil;
import cc.itsc.rbc.api.utils.HttpUtil;
import cc.itsc.rbc.api.utils.basic.ResultCodeEnum;
import cc.itsc.rbc.api.utils.basic.ResultMessageConst;
import cc.itsc.utils.common.MessageUtil;
import cc.itsc.utils.common.RegexUtils;
import cc.itsc.utils.constant.AccountConst;
import cc.itsc.utils.protobuf.account.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 提供用户账号相关的服务
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/6 21:27
 */

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private RpcAccountService rpcAccountService;
    @Autowired
    private RedisCacheService redisCacheService;

    @Override
    public ServiceResponseMessage loginOrRegister(UsernameAndPasswordReq usernameAndPasswordReq) {
        // 转换请求数据
        UsernameAndPassword usernameAndPassword = EDSUtil.toProtobufMessage(usernameAndPasswordReq);
        Profile profile;
        if (usernameAndPasswordReq.isRegister()) {
            // 用户注册
            profile = rpcAccountService.signUp(usernameAndPassword);
        } else {
            // 用户登录
            profile = rpcAccountService.login(usernameAndPassword);
        }
        // 判断处理结果的正确性
        if (profile.getErrorCode() != AccountConst.ERROR_CODE_SUCCESS) {
            return AccountResponseMessage.getErrorServiceRespMsg(profile.getErrorCode());
        }
        // 创建/注册成功 写入Redis
        String token = loginBackToken(profile.getUin(), profile.getSecretKey());
        // 装置返回对象
        ProfileResp profileResp = EDSUtil.profile2ProfileResp(profile);
        profileResp.setToken(token);
        return ServiceResponseMessage.createBySuccessCodeMessage(profileResp);
    }


    @Override
    public SimpleProfileResp pullSimpleProfileByOpenId(String openId) {
        Long uin = DewOpenIdUtil.generateUin(openId);
        if (uin == null) {
            return null;
        }
        GeneralProfile generalProfile = rpcAccountService.pullGeneralProfile(ProfileFilterCond.newBuilder()
                .setUin(uin)
                .addAllKeys(MessageUtil.getProtobufKeys(SimpleProfileResp.class))
                .build());
        if (generalProfile.getProfileMap().size() != 0) {
            SimpleProfileResp simpleProfileResp = MessageUtil.buildCommonBeanByMap(generalProfile.getProfileMap(), SimpleProfileResp.class, Collections.singleton("openId"));
            if (simpleProfileResp != null) {
                simpleProfileResp.setOpenId(openId);
                return simpleProfileResp;
            }
        }
        return null;
    }

    @Override
    public ServiceResponseMessage improveUserInformation(NewProfileReq profileReq) {
        if (!profileReq.getCode().equals(redisCacheService.getEmailVerifyCode(profileReq.getEmail()))) {
            return ServiceResponseMessage.createByFailCodeMessage(ResultMessageConst.EMAIL_VERIFY_CODE_ERROR);
        }
        int error = rpcAccountService.improveUserRegistration(EDSUtil.toProtobufMessage(profileReq)).getCode();
        if (error == AccountConst.ERROR_CODE_SUCCESS) {
            error = rpcAccountService.refreshLocation(EDSUtil.toProtobufMessage(profileReq.getLocationReq())).getCode();
        } else {
            return ServiceResponseMessage.createByFailCodeMessage(ResultMessageConst.COMPLETE_PROFILE_FAIL);
        }
        if (error != AccountConst.ERROR_CODE_SUCCESS) {
            return ServiceResponseMessage.createByFailCodeMessage(ResultMessageConst.UPDATE_USER_LOCATION_FAIL);
        } else {
            return ServiceResponseMessage.createBySuccessCodeMessage(ResultMessageConst.COMPLETE_PROFILE_SUCCESS);
        }
    }

    @Override
    public ServiceResponseMessage refreshUserProfile() {
        Long uin = HttpUtil.getUserUin();
        List<String> refreshUserProfileKeys = MessageUtil.getProtobufKeys(UsrProfileResp.class);
        refreshUserProfileKeys.addAll(MessageUtil.getProtobufKeys(ProfileStatusResp.class));
        GeneralProfile generalProfile = rpcAccountService.pullGeneralProfile(ProfileFilterCond.newBuilder()
                .setUin(uin)
                .addAllKeys(refreshUserProfileKeys)
                .build());
        if (generalProfile.getProfileMap().size() != 0) {
            UsrProfileResp usrProfileResp = MessageUtil.buildCommonBeanByMap(generalProfile.getProfileMap(), UsrProfileResp.class, Collections.emptySet());
            ProfileStatusResp profileStatusResp = MessageUtil.buildCommonBeanByMap(generalProfile.getProfileMap(), ProfileStatusResp.class, Collections.emptySet());
            RefreshProfileResp refreshProfileResp = new RefreshProfileResp();
            refreshProfileResp.setProfileStatusResp(profileStatusResp);
            refreshProfileResp.setUsrProfileResp(usrProfileResp);
            return ServiceResponseMessage.createBySuccessCodeMessage(refreshProfileResp);
        }
        return ServiceResponseMessage.createByFailCodeMessage(ResultMessageConst.CALL_RPC_ACCOUNT_SVR_ERROR);
    }

    @Override
    public void logout() {
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        AuthRealm authRealm = (AuthRealm) securityManager.getRealms().iterator().next();
        authRealm.getAuthenticationCache().put(HttpUtil.getUserUin(), new SimpleAuthenticationInfo(
                System.currentTimeMillis(), "Logout", UUID.randomUUID().toString()));
    }

    @Override
    public ServiceResponseMessage refreshLocation(LocationReq locationReq) {
        rpcAccountService.refreshLocation(EDSUtil.toProtobufMessage(locationReq));
        return ServiceResponseMessage.createBySuccessCodeMessage(ResultMessageConst.UPDATE_USER_LOCATION_SUCCESS);
    }

    @Override
    public ServiceResponseMessage resetUserPassword(String email, String code, String password) {
        if (!RegexUtils.isEmail(email)) {
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.EMAIL_VERIFY_ERROR.getCode(), ResultMessageConst.EMAIL_WORD_TYPE_ERROR);
        } else if (code.length() != 6 || !redisCacheService.getEmailVerifyCode(email).toUpperCase().equals(code.toUpperCase())) {
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.UNAUTHORIZED.getCode(), ResultMessageConst.EMAIL_VERIFY_CODE_ERROR);
        }
        int error = rpcAccountService.resetPassword(ResetPassword.newBuilder()
                .setUin(HttpUtil.getUserUin())
                .setPassword(password)
                .setEmail(email)
                .build())
                .getCode();
        if (error != AccountConst.ERROR_CODE_SUCCESS) {
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.RPC_SVR_ERROR_ACCOUNT.getCode(), ResultMessageConst.RPC_ACCOUNT_SVR_OPERATION_ERROR);
        }
        return ServiceResponseMessage.createBySuccessCodeMessage(ResultMessageConst.RESET_USER_PASSWORD_SUCCESS);
    }

    @Override
    public ServiceResponseMessage resetUserProfile(Map<String, String> modifyMap) {
        if (modifyMap == null || modifyMap.isEmpty()) {
            return ServiceResponseMessage.createByFailCodeMessage(
                    ResultCodeEnum.UNAUTHORIZED.getCode(),
                    ResultMessageConst.RESET_PROFILE_PARAMS_IS_NULL);
        } else {
            int error = rpcAccountService.pushGeneralProfile(GeneralProfile.newBuilder()
                    .setUin(HttpUtil.getUserUin())
                    .putAllProfile(modifyMap)
                    .build())
                    .getCode();
            if (error == AccountConst.ERROR_CODE_SUCCESS) {
                return ServiceResponseMessage.createBySuccessCodeMessage(
                        ResultCodeEnum.SUCCESS_RESET_CONTENT.getCode(),
                        ResultMessageConst.RESET_PROFILE_PARAMS_SUCCESS);
            } else {
                return ServiceResponseMessage.createByFailCodeMessage(
                        ResultCodeEnum.RPC_SVR_ERROR_ACCOUNT.getCode(),
                        ResultMessageConst.RESET_PROFILE_PARAMS_FAIL);
            }
        }

    }

    private String loginBackToken(Long uin, String secretKey) {
        // 获取权限处理器
        String token = JWTUtil.createToken(String.valueOf(uin), secretKey);
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        AuthRealm authRealm = (AuthRealm) securityManager.getRealms().iterator().next();
        // 直接清除Token,插入新Token
        authRealm.getAuthenticationCache().put(uin, new SimpleAuthenticationInfo(uin, token, authRealm.getName()));
        return token;
    }
}
