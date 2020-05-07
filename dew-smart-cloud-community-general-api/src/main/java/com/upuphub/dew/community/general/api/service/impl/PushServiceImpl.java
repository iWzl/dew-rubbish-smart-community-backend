package com.upuphub.dew.community.general.api.service.impl;

import com.upuphub.dew.community.connection.common.RegexUtils;
import com.upuphub.dew.community.connection.constant.PushConst;
import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.message.MessagePayload;
import com.upuphub.dew.community.connection.protobuf.push.EmailTemplateAndParams;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.service.PushService;
import com.upuphub.dew.community.general.api.service.RedisCacheService;
import com.upuphub.dew.community.general.api.service.remote.DewPushService;
import com.upuphub.dew.community.general.api.utils.EDSUtil;
import com.upuphub.dew.community.general.api.utils.basic.ResultCodeEnum;
import com.upuphub.dew.community.general.api.utils.basic.ResultMessageConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.UUID;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:56
 */
@Service
@Slf4j
public class PushServiceImpl implements PushService {
    @Resource
    DewPushService dewPushService;
    @Resource
    RedisCacheService redisCacheService;

    private final static String DEW_EMAIL_TEMPLATE_CODE = "dew_welcome_code_template";
    private final static String DEW_EMAIL_TEMPLATE_CODE_PARAMS = "code";

    @Override
    public ServiceResponseMessage sendEmailVerifyCode(String email) {
        String code = UUID.randomUUID().toString().substring(2, 8);
        if (!RegexUtils.isEmail(email)) {
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.EMAIL_VERIFY_ERROR.getCode(), ResultMessageConst.EMAIL_WORD_TYPE_ERROR);
        }
        // 发送验证邮件
        if (email.endsWith("@tz.itsc.cc")) {
            email = "994318935@qq.com";
        } else if (email.endsWith("@tw.itsc.cc")) {
            email = "isWangzl@aliyun.com";
        }
        EmailTemplateAndParams emailTemplateAndParams = EDSUtil.toProtobufMessage(email, DEW_EMAIL_TEMPLATE_CODE, Collections.singletonMap(DEW_EMAIL_TEMPLATE_CODE_PARAMS, code));
        redisCacheService.putEmailVerifyCode(email, code);
        RpcResultCode resultCode = dewPushService.sendEmailWithTemplateCode(emailTemplateAndParams);
        if (resultCode.getCode() == PushConst.ERROR_CODE_SUCCESS) {
            return ServiceResponseMessage.createBySuccessCodeMessage(ResultMessageConst.EMAIL_CODE_SEND_SUCCESS, code);
        } else {
            return ServiceResponseMessage.createBySuccessCodeMessage(ResultMessageConst.EMAIL_VERIFY_CODE_ERROR, code);
        }
    }

    @Override
    public void fireMessageHasArrived(MessagePayload messagePayload) {
        RpcResultCode resultCode = dewPushService.fireMessageHasArrived(messagePayload);
        if (resultCode.getCode() != PushConst.ERROR_CODE_SUCCESS) {
            log.error("Notify Error ");
        }
    }
}
