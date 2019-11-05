package com.upuphub.dew.community.general.api.bean.vo.resp;

import lombok.Data;

@Data
public class ProfileStatusResp {
    private String completelyRegistrationFlag;
    private String flagEmailVerify;
    private String uinStatus;
    private String uinType;
}
