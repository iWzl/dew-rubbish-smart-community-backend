package com.upuphub.dew.community.push.controller;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.push.EmailTemplateAndParams;
import com.upuphub.dew.community.connection.protobuf.push.SyncMachineHealth;
import com.upuphub.dew.community.connection.protobuf.push.SyncMachineSearchInfo;
import com.upuphub.dew.community.push.component.sender.MailGunSender;
import com.upuphub.dew.community.push.service.MailService;
import com.upuphub.dew.community.push.service.PushService;
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
    @Autowired
    PushService pushService;

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

    @PostMapping("/push/machine/health/fire")
    public RpcResultCode syncMachineHealthInfo(@RequestBody SyncMachineHealth syncMachineHealth) {
        return RpcResultCode.newBuilder().setCode(
                pushService.fireMachineHealthInfoWithUin(syncMachineHealth.getUin(),syncMachineHealth.getTimestamp())
        ).build();
    }


    @PostMapping("/push/machine/search/fire")
    public RpcResultCode syncMachineSearch(@RequestBody SyncMachineSearchInfo syncMachineSearchInfo) {
        return RpcResultCode.newBuilder().setCode(
                pushService.fireMachineSearch(syncMachineSearchInfo)
        ).build();
    }
}
