package com.upuphub.dew.community.general.api.bean.vo.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/4 23:30
 */

@Data
public class ProfileResp {
    @JsonProperty
    private Long uin;
    @JsonProperty
    private String openId;
    @JsonProperty
    private String token;
    @JsonProperty
    private Integer profileCompletion;
    @JsonProperty
    private UsrProfileResp usrProfile;
    @JsonProperty
    private UsrSettingResp usrSetting;
    @JsonProperty
    private UsrStatusFlagResp usrStatusFlag;
    @JsonProperty
    private LastLoginInfoResp lastLoginInfo;

}
