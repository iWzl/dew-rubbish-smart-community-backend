package com.upuphub.dew.community.account.bean.po;

import lombok.Data;

@Data
public class AccountLoginPO{
    /**
     * 登录表主键
     */
    private Long id;
    /**
     * 登录使用的关键字
     */
    private String loginKey;
    /**
     * 关键字对应的用户uin
     */
    private Long uin;

    private String idType;

    private String status;

    private Long createTime;
}
