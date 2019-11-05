package com.upuphub.dew.community.account.bean.po;

import lombok.Data;

@Data
public class AccountTrackPO {
    /**
     * 账户登录信息
     */
    private Long id;

    /**
     * 账户uin
     */
    private Long uin;

    /**
     * 手机IMEI
     */
    private String imei;

    /**
     * IP地址
     */
    private String ip;

    /**
     * APP版本号
     */
    private String appVersion;

    /**
     * 系统版本号
     */
    private String systemVersion;

    /**
     * 手机型号
     */
    private String deviceModel;

    /**
     * 手机设备名称
     */
    private String deviceName;

    private Long createTime;
}
