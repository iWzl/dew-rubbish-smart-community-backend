package com.upuphub.dew.community.operation.api.service.remote;

import com.upuphub.dew.community.connection.protobuf.account.*;
import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.operation.api.config.ProtoFeignConfiguration;
import com.upuphub.dew.community.operation.api.service.remote.sentinel.DewAccountServiceSentinel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/11 00:25
 */

@FeignClient(value = "dew-smart-community-account",configuration = ProtoFeignConfiguration.class,fallback = DewAccountServiceSentinel.class)
public interface DewAccountService {

    /**
     * 用户注册
     *
     * @param usernameAndPassword 用户账号和密码相关信息
     * @return 用户注册完成后的Profile信息
     */
    @PostMapping(value = "/signUp",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    public Profile signUp(@RequestBody UsernameAndPassword usernameAndPassword);

    /**
     * 用户登录
     *
     * @param usernameAndPassword 用户账号和密码相关信息
     * @return 用户完成登录后的Profile信息
     */
    @PostMapping(value = "/login",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    public Profile login(@RequestBody UsernameAndPassword usernameAndPassword);

    /**
     * 完善用户基础注册信息
     *
     * @param baseProfile 用户注册后的基础用户信息
     * @return 用户信息完善后的处理状态
     */
    @PostMapping(value = "profile/commit",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    public RpcResultCode improveUserRegistration(@RequestBody BaseProfile baseProfile);

    /**
     * 刷新用户地理位置信息
     *
     * @param location 用户新的地理位置信息
     * @return 用户信息完善后的处理状态
     */
    @PostMapping(value = "profile/location/commit",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    public RpcResultCode refreshLocation(@RequestBody Location location);

    /**
     * 设置通用的用户Profile信息
     *
     * @param generalProfile 通用用户Profile信息
     * @return 用户通用Profile信息的处理结果状态
     */
    @PostMapping(value = "profile/push",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    public RpcResultCode pushGeneralProfile(@RequestBody GeneralProfile generalProfile);


    /**
     * 获取通用的用户Profile信息
     *
     * @param profileFilterCond 通用用户Profile信息
     * @return 用户通用Profile信息的处理结果状态
     */
    @PostMapping(value = "profile/pull",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    public GeneralProfile pullGeneralProfile(@RequestBody ProfileFilterCond profileFilterCond);


    /**
     * 修改用户账户密码
     *
     * @param resetPassword 需要重置的用户账号和密码
     * @return 重置密码完成后的账户处理涨状态
     */
    @PostMapping(value = "profile/password",consumes = "application/x-protobuf")
    public RpcResultCode resetPassword(@RequestBody ResetPassword resetPassword);
}
