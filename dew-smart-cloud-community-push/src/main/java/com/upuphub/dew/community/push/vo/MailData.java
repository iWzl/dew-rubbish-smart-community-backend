package com.upuphub.dew.community.push.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/13 22:08
 */
@Data
public class MailData {
    String subject; // 邮件主题
    String[] to; // 邮件收件人
    String content; // 邮件内容
    String templateName; // 邮件模板名称
    Map<String,String> inlinePath; // 背景图片
    Map<String, Object> param; // 参数集（邮件模板中的动态变量值）
    Map<String,String> attachment; // 附件集合
}
