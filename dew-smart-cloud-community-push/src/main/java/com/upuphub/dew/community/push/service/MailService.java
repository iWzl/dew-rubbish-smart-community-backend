package com.upuphub.dew.community.push.service;


import com.upuphub.dew.community.push.vo.MailData;

import java.util.Map;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/13 22:05
 */
public interface MailService {

    /**
     * 发送验证码邮件
     * @param to 邮件接收者
     * @param code 验证码
     * @return 邮件的发送状态
     */
    Integer sendVerificationCodeMail(String to, String code);


    /**
     * 发送通用邮件
     * @param mailData 发送邮件的数据
     * @return 邮件的发送状态
     */
    Integer sendMail(MailData mailData);

    /**
     * 使用邮件ID和单个发送邮件相关信息
     *
     * @param email 邮件地址
     * @param templateId 邮件需要的发送的模板ID
     * @param replaceParametersMap 邮件模板中的需要替代的模板参数
     */
    Integer sendEmailWithTemplateId(String email, String templateId, Map<String, String> replaceParametersMap);
}
