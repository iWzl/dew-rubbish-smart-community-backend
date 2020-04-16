package com.upuphub.dew.community.push.controller;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.push.EmailTemplateAndParams;
import com.upuphub.dew.community.push.component.sender.MailGunSender;
import com.upuphub.dew.community.push.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class PushController {
    @Autowired
    MailService mailService;
    @Autowired
    MailGunSender mailGunSender;

    /**
     * 发送指定模板的邮件
     *
     * @param emailTemplateAndParams Email模板和其他待发送参数
     * @return 邮件发送调用的处理结果
     */
    @PostMapping("/push/email/template")
    public RpcResultCode sendEmailWithTemplateCode(@RequestBody EmailTemplateAndParams emailTemplateAndParams) {
       return RpcResultCode.newBuilder().setCode(
               mailGunSender.sendEmailWithTemplateCode(
                       emailTemplateAndParams.getEmail(),
                       emailTemplateAndParams.getTemplateId(),
                       emailTemplateAndParams.getReplaceParametersMap())
       ).build();
    }
}
