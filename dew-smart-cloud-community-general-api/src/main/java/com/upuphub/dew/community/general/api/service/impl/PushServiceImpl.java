package com.upuphub.dew.community.general.api.service.impl;

import com.google.protobuf.Message;
import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.common.RegexUtils;
import com.upuphub.dew.community.connection.constant.MqttConst;
import com.upuphub.dew.community.connection.protobuf.push.EmailAndCode;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.service.MqttSenderService;
import com.upuphub.dew.community.general.api.service.PushService;
import com.upuphub.dew.community.general.api.service.RedisCacheService;
import com.upuphub.dew.community.general.api.service.remote.DewAccountService;
import com.upuphub.dew.community.general.api.utils.basic.ResultCodeEnum;
import com.upuphub.dew.community.general.api.utils.basic.ResultMessageConst;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:56
 */
@Service
public class PushServiceImpl implements PushService {
    @Resource
    DewAccountService romoteAccountService;
    @Resource
    MqttSenderService mqttSenderService;
    @Resource
    RedisCacheService redisCacheService;


    @Override
    public ServiceResponseMessage sendEmailVerifyCode(String email) {
        String code = UUID.randomUUID().toString().substring(2,8);
        if(!RegexUtils.isEmail(email)){
            return ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.EMAIL_VERIFY_ERROR.getCode(), ResultMessageConst.EMAIL_WORD_TYPE_ERROR);
        }
        // 发送验证邮件
        // todo 这里测试添加的部分代码,完成后考虑删除
        Message emailAndCode;
        if(email.endsWith("@tz.upuphub.com") ){
            emailAndCode = EmailAndCode.newBuilder().setCode(code).setEmail("994318935@qq.com").build();
        }
        else if(email.endsWith("@tw.upuphub.com")){
            emailAndCode = EmailAndCode.newBuilder().setCode(code).setEmail("wangzl@wangzl.cc").build();
        }else {
            emailAndCode = EmailAndCode.newBuilder().setCode(code).setEmail(email).build();
        }
        String payload = MessageUtil.buildBase64MqttMessage(MqttConst.TYPE_RBC_API_EMAIL_CODE,MqttConst.TYPE_RBC_API_EMAIL_CODE,emailAndCode);
        mqttSenderService.sendToMqtt(MqttConst.TOPIC_RBC_API_SVT,2,payload);
        redisCacheService.putEmailVerifyCode(email,code);
        return ServiceResponseMessage.createBySuccessCodeMessage(ResultMessageConst.EMAIL_CODE_SEND_SUCCESS);
    }
}
