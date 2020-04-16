package com.upuphub.dew.community.push.bean.po;

import lombok.Data;

@Data
public class MailTemplatePO {
    /**
     * 邮件码
     */
    private String code;

    /**
     * 邮件的模板属性
     */
    private String template;

    /**
     * 发送者
     */
    private String sender;

    /**
     * 邮件发送者
     */
    private String from;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 模板类型
     */
    private String type;


    /**
     * 邮件标记标签
     */
    private String tag;

    /**
     * 模板版本
     */
    private Integer version;

    /**
     * 模板状态
     */
    private Integer status;
}

