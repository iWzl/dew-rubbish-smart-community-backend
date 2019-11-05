package com.upuphub.dew.community.account.service;
import com.upuphub.dew.community.account.utils.ObjectUtil;
import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.constant.MqttConst;
import com.upuphub.dew.community.connection.protobuf.mqtt.ProfileMapMessage;
import com.upuphub.profile.component.ProfileParametersManager;
import com.upuphub.profile.service.BaseProfileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/18 21:10
 */

@Service
public class ProfileService extends BaseProfileService {
    @Resource
    private ProfileMongoService profileMongoService;
    @Resource
    private MqttSenderService mqttSenderService;


    public ProfileService(ProfileParametersManager profileParametersManager) {
        super(profileParametersManager);
    }

    @Override
    protected void handlingProfileSpread(Map<String, Object> spreadProfileMap) {
        Long  uin = (Long) spreadProfileMap.get("uin");
        spreadProfileMap.remove("uin");
        ProfileMapMessage profileMapMessage = ProfileMapMessage.newBuilder()
                .setUin(uin)
                .putAllProfile(ObjectUtil.map2StringMap(spreadProfileMap))
                .build();
        mqttSenderService.sendToMqtt(1, MessageUtil.buildBase64MqttMessage(
                MqttConst.TAG_ACCOUNT_PROFILE,
                MqttConst.TYPE_ACCOUNT_PROFILE_MAP,
                profileMapMessage)
        );
    }

    @Override
    protected boolean handlingProfileVerify(Map<String, Object> verifyProfileMap) {
        return true;
    }


    /**
     * 清理Profile
     *
     * @param uin 需要清理的Profile信息
     */
    public void cleanProfileByUin(Long uin) {
        profileMongoService.cleanProfileByUin(uin);
    }

    /**
     * 判断用户Profile是否存在
     *
     * @param uin 用户Uin
     * @return 用户Uin的存在状态
     */
    public boolean checkProfileByUin(long uin) {
        return profileMongoService.checkProfileByUin(uin);
    }
}
