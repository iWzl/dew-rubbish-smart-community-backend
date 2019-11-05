package com.upuphub.dew.community.general.api.service.remote.sentinel;

import com.upuphub.dew.community.connection.protobuf.account.*;
import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.general.api.exception.RpcServiceConnectionException;
import com.upuphub.dew.community.general.api.service.remote.DewAccountService;
import org.springframework.stereotype.Component;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/6 21:52
 */
@Component
public class DewAccountServiceSentinel implements DewAccountService {

    @Override
    public Profile signUp(UsernameAndPassword usernameAndPassword) {
        throw new RpcServiceConnectionException("Call Rpc Account Model Error");
    }

    @Override
    public Profile login(UsernameAndPassword usernameAndPassword) {
        throw new RpcServiceConnectionException("Call Rpc Account Model Error");
    }

    @Override
    public RpcResultCode improveUserRegistration(BaseProfile baseProfile) {
        throw new RpcServiceConnectionException("Call Rpc Account Model Error");
    }

    @Override
    public RpcResultCode refreshLocation(Location location) {
        throw new RpcServiceConnectionException("Call Rpc Account Model Error");
    }

    @Override
    public RpcResultCode pushGeneralProfile(GeneralProfile generalProfile) {
        throw new RpcServiceConnectionException("Call Rpc Account Model Error");
    }

    @Override
    public GeneralProfile pullGeneralProfile(ProfileFilterCond profileFilterCond) {
        throw new RpcServiceConnectionException("Call Rpc Account Model Error");
    }

    @Override
    public RpcResultCode resetPassword(ResetPassword resetPassword) {
        throw new RpcServiceConnectionException("Call Rpc Account Model Error");
    }
}
