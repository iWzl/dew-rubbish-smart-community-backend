package com.upuphub.dew.community.operation.api.bean.vo.resp;

import lombok.Data;

@Data
public class ProfileStatusResp {
    private String completelyRegistrationFlag;
    private String flagEmailVerify;
    private String uinStatus;
    private String uinType;
}
