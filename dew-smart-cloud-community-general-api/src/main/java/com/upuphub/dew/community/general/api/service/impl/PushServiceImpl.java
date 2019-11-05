package com.upuphub.dew.community.general.api.service.impl;

import cc.itsc.rbc.api.bean.vo.common.ServiceResponseMessage;
import cc.itsc.rbc.api.service.MqttSenderService;
import cc.itsc.rbc.api.service.PushService;
import cc.itsc.rbc.api.service.RedisCacheService;
import cc.itsc.rbc.api.service.rpc.RpcAccountService;
import cc.itsc.rbc.api.utils.basic.ResultCodeEnum;
import cc.itsc.rbc.api.utils.basic.ResultMessageConst;
import cc.itsc.utils.common.MessageUtil;
import cc.itsc.utils.common.RegexUtils;
import cc.itsc.utils.constant.MqttConst;
import cc.itsc.utils.protobuf.push.EmailAndCode;
import com.google.protobuf.Message;
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
    RpcAccountService rpcAccountService;
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
