package com.upuphub.dew.community.operation.api.service.remote.sentinel;

import com.upuphub.dew.community.connection.protobuf.account.*;
import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.operation.api.service.remote.DewAccountService;
import org.springframework.stereotype.Component;

@Component
public class DewAccountServiceSentinel implements DewAccountService {
    @Override
    public Profile signUp(UsernameAndPassword usernameAndPassword) {
        return null;
    }

    @Override
    public Profile login(UsernameAndPassword usernameAndPassword) {
        return null;
    }

    @Override
    public RpcResultCode improveUserRegistration(BaseProfile baseProfile) {
        return null;
    }

    @Override
    public RpcResultCode refreshLocation(Location location) {
        return null;
    }

    @Override
    public RpcResultCode pushGeneralProfile(GeneralProfile generalProfile) {
        return null;
    }

    @Override
    public GeneralProfile pullGeneralProfile(ProfileFilterCond profileFilterCond) {
        return null;
    }

    @Override
    public RpcResultCode resetPassword(ResetPassword resetPassword) {
        return null;
    }
}
