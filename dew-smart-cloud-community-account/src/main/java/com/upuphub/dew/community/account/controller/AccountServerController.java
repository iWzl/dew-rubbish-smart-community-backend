package com.upuphub.dew.community.account.controller;


import com.upuphub.dew.community.account.service.AccountService;
import com.upuphub.dew.community.account.utils.ObjectUtil;
import com.upuphub.dew.community.connection.constant.AccountConst;
import com.upuphub.dew.community.connection.protobuf.account.*;
import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/6 20:41
 */

@RestController
@RequestMapping(consumes = "application/x-protobuf", produces = "application/x-protobuf")
public class AccountServerController {

    @Autowired
    AccountService accountService;

    /**
     * 用户注册
     *
     * @param usernameAndPassword 用户账号和密码相关信息
     * @return 用户注册完成后的Profile信息
     */
    @PostMapping(value = "/signUp")
    public Profile signUp(@RequestBody UsernameAndPassword usernameAndPassword) {
        return accountService.registered(usernameAndPassword);
    }

    /**
     * 用户登录
     *
     * @param usernameAndPassword 用户账号和密码相关信息
     * @return 用户完成登录后的Profile信息
     */
    @PostMapping(value = "/login")
    public Profile login(@RequestBody UsernameAndPassword usernameAndPassword) {
        return accountService.login(usernameAndPassword);
    }

    /**
     * 完善用户基础注册信息
     *
     * @param baseProfile 用户注册后的基础用户信息
     * @return 用户信息完善后的处理状态
     */
    @PostMapping(value = "profile/commit")
    public RpcResultCode improveUserRegistration(@RequestBody BaseProfile baseProfile) {
        return RpcResultCode.newBuilder().setCode(
                accountService.improveUserRegistration(baseProfile))
                .build();
    }

    /**
     * 刷新用户地理位置信息
     *
     * @param location 用户新的地理位置信息
     * @return 用户信息完善后的处理状态
     */
    @PostMapping(value = "profile/location/commit")
    public RpcResultCode refreshLocation(@RequestBody Location location) {
        return RpcResultCode.newBuilder().setCode(
                accountService.refreshLocation(location)
        ).build();
    }


    /**
     * 设置通用的用户Profile信息
     *
     * @param generalProfile 通用用户Profile信息
     * @return 用户通用Profile信息的处理结果状态
     */
    @PostMapping(value = "profile/push")
    public RpcResultCode pushGeneralProfile(@RequestBody GeneralProfile generalProfile) {
        int error = accountService.pushGeneralProfile(
                generalProfile.getUin(),
                ObjectUtil.stringMap2Map(generalProfile.getProfileMap())
        );
        return RpcResultCode.newBuilder().setCode(
                error > 0 ? AccountConst.ERROR_CODE_SUCCESS : AccountConst.ERROR_CODE_COMMON_FAIL
        ).build();
    }


    /**
     * 获取通用的用户Profile信息
     *
     * @param profileFilterCond 通用用户Profile信息
     * @return 用户通用Profile信息的处理结果状态
     */
    @PostMapping(value = "profile/pull")
    public GeneralProfile pullGeneralProfile(@RequestBody ProfileFilterCond profileFilterCond) {
        return GeneralProfile.newBuilder()
                .setUin(profileFilterCond.getUin())
                .putAllProfile(ObjectUtil.map2StringMap(
                        accountService.pullGeneralProfile(profileFilterCond.getUin(), profileFilterCond.getKeysList()))
                )
                .build();
    }

    /**
     * 修改用户账户密码
     *
     * @param resetPassword 需要重置的用户账号和密码
     * @return 重置密码完成后的账户处理涨状态
     */
    @PostMapping(value = "profile/password")
    public RpcResultCode resetPassword(@RequestBody ResetPassword resetPassword) {
        return RpcResultCode.newBuilder().setCode(
                accountService.resetPassword(resetPassword)
        ).build();
    }
}
