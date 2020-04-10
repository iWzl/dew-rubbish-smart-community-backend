package com.upuphub.dew.community.account.bean.po;

import lombok.Data;

@Data
public class AccountUinPO {
    /**
     * 用户唯一标识
     */
    private Long uin;

    /**
     * 用户注册使用的账户
     */
    private String id;
    private String idType;
    private String secret;
    private String password;
    private String status;
    private String type;
    private String platform;
    private String product;
    private Long createTime;
    private Long updateTime;
}
