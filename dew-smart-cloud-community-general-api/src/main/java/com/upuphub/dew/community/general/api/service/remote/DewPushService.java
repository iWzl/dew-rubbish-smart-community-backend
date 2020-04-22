package com.upuphub.dew.community.general.api.service.remote;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.push.EmailAndCode;
import com.upuphub.dew.community.connection.protobuf.push.EmailTemplateAndParams;
import com.upuphub.dew.community.general.api.config.ProtoFeignConfiguration;
import com.upuphub.dew.community.general.api.service.remote.sentinel.DewPushServiceSentinel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:39
 */

@FeignClient(value = "dew-smart-community-push",configuration = ProtoFeignConfiguration.class,fallback = DewPushServiceSentinel.class)
public interface DewPushService {

    /**
     * 发送指定模板的邮件
     *
     * @param emailTemplateAndParams Email模板和其他待发送参数
     * @return 邮件发送调用的处理结果
     */
    @PostMapping(value = "/push/email/template",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    public RpcResultCode sendEmailWithTemplateCode(@RequestBody EmailTemplateAndParams emailTemplateAndParams);
}
