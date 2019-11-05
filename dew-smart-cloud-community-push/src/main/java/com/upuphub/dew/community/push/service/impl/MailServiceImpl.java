package com.upuphub.dew.community.push.service.impl;

import com.upuphub.dew.community.connection.constant.PushConst;
import com.upuphub.dew.community.push.service.MailService;
import com.upuphub.dew.community.push.vo.MailData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 发送邮件相关服务的实现
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/13 22:06
 */

@Slf4j
@Service
public class MailServiceImpl implements MailService {
    @Value("${spring.mail.username}")
    private String senderMailAddress;

    @Resource
    private TemplateEngine templateEngine;
    @Resource
    private JavaMailSender javaMailSender;

    @Override
    public Integer sendVerificationCodeMail(String to, String code) {
        MailData mailData = new MailData();
        mailData.setSubject("Rubbish Community 验证码");
        mailData.setContent("Rubbish Community 邮箱验证码");
        mailData.setTo(new String[]{to});
        mailData.setTemplateName("verification");
        Map<String, Object> param = new HashMap<>();
        param.put("code", code.toUpperCase());
        mailData.setParam(param);
        return sendMail(mailData);
    }

    @Override
    public Integer sendMail(MailData mailData) {
        MimeMessage mimeMessage;
        try {
            mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            // 设置发件人邮箱
            helper.setFrom(senderMailAddress);
            // 设置收件人邮箱
            helper.setTo(mailData.getTo());
            // 设置邮件标题
            helper.setSubject(mailData.getSubject());
            // 添加正文（使用thymeleaf模板）
            Context context = new Context();
            context.setVariables(mailData.getParam());
            String content = this.templateEngine.process(mailData.getTemplateName(), context);
            helper.setText(content, true);
            // 添加背景图片
            if (mailData.getInlinePath() != null) {
                for (Map.Entry<String, String> entry : mailData.getInlinePath().entrySet()) {
                    helper.addInline(
                            entry.getKey(),
                            new FileSystemResource(new File(entry.getValue())));
                }
            }
            // 添加附件
            if (mailData.getAttachment() != null) {
                for (Map.Entry<String, String> entry : mailData.getAttachment().entrySet()) {
                    helper.addAttachment(
                            entry.getKey(),
                            new FileSystemResource(new File(entry.getValue())));
                }
            }
            // 发送邮件
            javaMailSender.send(mimeMessage);
            return PushConst.ERROR_CODE_SUCCESS;
        } catch (MessagingException e) {
            log.error("Mail Send Error", e);
            return PushConst.ERROR_CODE_COMMON_FAIL;
        }
    }
}
