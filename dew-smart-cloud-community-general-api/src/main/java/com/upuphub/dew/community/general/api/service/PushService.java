package com.upuphub.dew.community.general.api.service;

import cc.itsc.rbc.api.bean.vo.common.ServiceResponseMessage;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:55
 */
public interface PushService {
    /**
     * 发送邮箱验证码
     * @return 发送的状态
     * @param email 邮箱地址
     */
    public ServiceResponseMessage sendEmailVerifyCode(String email);

}
