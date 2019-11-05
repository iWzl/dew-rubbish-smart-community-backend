package com.upuphub.dew.community.account.bean.po;

import lombok.Data;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/4 20:18
 */
@Data
public class AccountBasicPO {

    private Long uin;
    private String idType;
    private String secret;
    private String accountStatus;
    private String accountType;
    private String loginStatus;
    private String platform;
    private Long createTime;
    private Long updateTime;
}
