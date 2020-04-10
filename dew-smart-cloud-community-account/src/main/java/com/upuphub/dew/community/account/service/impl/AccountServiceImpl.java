package com.upuphub.dew.community.account.service.impl;

import com.upuphub.dew.community.account.bean.dto.ACCOUNT_ID_TYPE;
import com.upuphub.dew.community.account.bean.dto.ACCOUNT_LOGIN_VERIFY_STATUS;
import com.upuphub.dew.community.account.bean.dto.ACCOUNT_STATUS;
import com.upuphub.dew.community.account.bean.dto.ACCOUNT_TYPE;
import com.upuphub.dew.community.account.bean.po.AccountBasicPO;
import com.upuphub.dew.community.account.bean.po.AccountLoginPO;
import com.upuphub.dew.community.account.bean.po.AccountTrackPO;
import com.upuphub.dew.community.account.bean.po.AccountUinPO;
import com.upuphub.dew.community.account.dao.AccountDao;
import com.upuphub.dew.community.account.service.AccountService;
import com.upuphub.dew.community.account.service.MqttSenderService;
import com.upuphub.dew.community.account.service.ProfileService;
import com.upuphub.dew.community.account.utils.CommonUtil;
import com.upuphub.dew.community.connection.common.JsonHelper;
import com.upuphub.dew.community.connection.common.MD5Utils;
import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.common.RegexUtils;
import com.upuphub.dew.community.connection.constant.AccountConst;
import com.upuphub.dew.community.connection.protobuf.account.*;
import com.upuphub.dew.community.connection.protocol.mqtt.PUSH_MESSAGE_TYPE;
import com.upuphub.dew.community.connection.protocol.mqtt.PushMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;


/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/18 21:10
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Value("${dew.password.salt}")
    private String passwordSalt = "RUBBISH-COMMUNITY_SVR";
    @Value("${dew.mqtt.topic.template}")
    private String topicTemplate;

    @Resource
    AccountDao accountDao;
    @Resource
    ProfileService profileService;
    @Resource
    MqttSenderService mqttSenderService;

    @Override
    public Profile registered(UsernameAndPassword usernameAndPassword) {
        // 查询是否存在记录
        AccountLoginPO accountLoginPO = accountDao.selectAccountLoginRecordByKey(usernameAndPassword.getUsername());
        if (accountLoginPO != null) {
            // 返回账户已存在
            return Profile.newBuilder().setErrorCode(AccountConst.ERROR_CODE_ALREADY_EXISTS).build();
        }
        // 校验邮箱格式规范
        if (usernameAndPassword.getIdType() == ACCOUNT_ID_TYPE.EMAIL.value()) {
            if (!RegexUtils.isEmail(usernameAndPassword.getUsername())) {
                // 不是合法的邮箱格式
                return Profile.newBuilder().setErrorCode(AccountConst.ERROR_CODE_NON_STANDARD_EMAIL).build();
            }
        }
        // 创建用户,初始化用户信息
        initAccountProfile(usernameAndPassword);
        // 注册成功,执行登录
        return login(usernameAndPassword);
    }

    @Override
    public Profile login(UsernameAndPassword usernameAndPassword) {
        // 查询是否存在记录
        AccountBasicPO accountBasic = accountDao.selectAccountBasicInfoWithKeyAndPwd(
                usernameAndPassword.getUsername(),
                MD5Utils.string2MD5WithSalt(usernameAndPassword.getPassword(), passwordSalt)
        );
        if (accountBasic == null) {
            return Profile.newBuilder().setErrorCode(AccountConst.ERROR_CODE_NOT_EXISTS).build();
        }
        AccountTrackPO accountTrackHistory = accountDao.selectAccountLastedLoginInfoByUin(accountBasic.getUin());
        // 追踪用户登录记录
        trackAccountLogin(accountBasic.getUin(), usernameAndPassword.getDevice());
        // 构建回报需要的Profile信息
        return buildLoginProfile(accountBasic,accountTrackHistory);
    }


    @Override
    public Integer improveUserRegistration(BaseProfile baseProfile) {
        long uin = baseProfile.getUin();
        // 判断用户是否存在
        if(uin == 0 || !profileService.checkProfileByUin(uin)){
            return AccountConst.ERROR_CODE_NOT_EXISTS;
        }
        Map<String,Object> profileMap = MessageUtil.buildMessageToMap(baseProfile);
        profileMap.remove("uin");
        profileMap.put("completelyRegistrationFlag",Boolean.TRUE.toString().toLowerCase());
        profileService.pushGeneralProfile(String.valueOf(uin),profileMap);
        int error =  accountDao.updateAccountLoginStatus(uin,
                Objects.requireNonNull(ACCOUNT_ID_TYPE.convert(baseProfile.getIdType())).name(),
                ACCOUNT_LOGIN_VERIFY_STATUS.ENABLE.name());
        if(error == 1){
            // 成功以后发出同步Profile信息的通知
            PushMessage pushMessage = new PushMessage(PUSH_MESSAGE_TYPE.SYNC_PROFILE.value(), System.currentTimeMillis());
            mqttSenderService.sendToMqtt(String.format(topicTemplate,baseProfile.getUin()),2, JsonHelper.allToJson(pushMessage));
            return AccountConst.ERROR_CODE_SUCCESS;
        }else {
            return AccountConst.ERROR_CODE_COMMON_FAIL;
        }
    }


    @Override
    public Integer refreshLocation(Location location) {
        long uin = location.getUin();
        // 判断用户是否存在
        if(!profileService.checkProfileByUin(uin)){
            return AccountConst.ERROR_CODE_NOT_EXISTS;
        }
        String profileKey = String.valueOf(uin);
        Map<String,Object> profileMap = MessageUtil.buildMessageToMap(location);
        profileMap.remove("uin");
        profileService.pushGeneralProfile(profileKey,profileMap);
        return AccountConst.ERROR_CODE_SUCCESS;
    }


    @Override
    public Integer pushGeneralProfile(Long uin, Map<String, Object> generalProfile) {
        if(generalProfile != null && generalProfile.size() > 0){
            return profileService.pushGeneralProfile(String.valueOf(uin),generalProfile);
        }
        return AccountConst.ERROR_CODE_COMMON_FAIL;
    }

    @Override
    public Map<String, Object> pullGeneralProfile(long uin, List<String> keys) {
        // 信息存在在profile中，转给profileService进行处理
        // 此外,需要Mysql中存储的信息在这个地方进行组装
        return profileService.pullGeneralProfile(String.valueOf(uin),keys);
    }

    /**
     * 追踪用户登录记录
     *
     * @param uin    用户UIN
     * @param device 用户登录的设备信息
     */
    private void trackAccountLogin(Long uin, UsernameAndPassword.Device device) {
        AccountTrackPO accountTrackPO = new AccountTrackPO();
        accountTrackPO.setUin(uin);
        accountTrackPO.setImei(device.getImei());
        accountTrackPO.setIp(device.getIp());
        accountTrackPO.setAppVersion(device.getAppVersion());
        accountTrackPO.setSystemVersion(device.getOSVersion());
        accountTrackPO.setDeviceModel(device.getSystemModel());
        accountTrackPO.setDeviceName(device.getDevName());
        accountTrackPO.setCreateTime(System.currentTimeMillis());
        accountDao.insertAccountTrack(accountTrackPO);
    }


    /**
     * 创建初始空白用户
     *
     * @param usernameAndPassword 空白用户的用户名和密码
     */
    private void initAccountProfile(UsernameAndPassword usernameAndPassword) {
        // 生成用户的唯一secretKey
        String secretKey = UUID.randomUUID().toString();
        // 生成用户唯一密码
        String secretPassword = MD5Utils.string2MD5WithSalt(usernameAndPassword.getPassword(), passwordSalt);
        // 组装Account信息
        AccountUinPO accountUinPO = new AccountUinPO();
        accountUinPO.setId(usernameAndPassword.getUsername());
        accountUinPO.setIdType(Objects.requireNonNull(ACCOUNT_ID_TYPE.convert(usernameAndPassword.getIdType())).name());
        accountUinPO.setSecret(secretKey);
        accountUinPO.setPassword(secretPassword);
        accountUinPO.setStatus(ACCOUNT_STATUS.ENABLE.name());
        accountUinPO.setType(ACCOUNT_TYPE.NORMAL.name());
        accountUinPO.setProduct(usernameAndPassword.getProduct());
        accountUinPO.setPlatform(usernameAndPassword.getDevice().getPlatform());
        accountUinPO.setCreateTime(System.currentTimeMillis());
        accountUinPO.setUpdateTime(System.currentTimeMillis());
        accountDao.insertAccountUin(accountUinPO);
        // 组装AccountLogin信息
        AccountLoginPO accountLoginPO = new AccountLoginPO();
        accountLoginPO.setUin(accountUinPO.getUin());
        accountLoginPO.setLoginKey(usernameAndPassword.getUsername());
        accountLoginPO.setStatus(ACCOUNT_LOGIN_VERIFY_STATUS.NO_VERIFY.name());
        accountLoginPO.setCreateTime(System.currentTimeMillis());
        accountLoginPO.setIdType(accountUinPO.getIdType());
        accountDao.insertAccountLogin(accountLoginPO);
        // 初始化用户Profile信息
        profileService.initGeneralProfile(String.valueOf(accountUinPO.getUin()));
    }


    /**
     * 构建登录成功后,回报的Profile信息
     * @param common 用户基本信息
     * @param track 用户登录信息
     * @return Profile的Protobuf对象
     */
    private Profile buildLoginProfile(AccountBasicPO common, AccountTrackPO track) {
        double threshold = 60;
        String completelyRegistrationFlag = "completelyRegistrationFlag";
        List<String> usrProfileList = MessageUtil.getProtobufKeys(UsrProfile.getDescriptor());
        usrProfileList.add(completelyRegistrationFlag);
        Map<String,Object> usrProfileMap = pullGeneralProfile(common.getUin(),usrProfileList);
        int profileCompletion = ((CommonUtil.calculateProfileValidData(usrProfileMap)-1) * 100) / (usrProfileMap.size()-1) ;
        UsrStatusFlag usrStatusFlag = UsrStatusFlag.newBuilder()
                .setEmailVerifiedFlag(ACCOUNT_LOGIN_VERIFY_STATUS.ENABLE.name().equals(common.getLoginStatus()))
                .setDisableFlag(!ACCOUNT_STATUS.ENABLE.name().equals(common.getAccountStatus()))
                .setNeedMoreInfoFlag(threshold > profileCompletion)
                .setCompletelyRegistrationFlag(Boolean.parseBoolean(String.valueOf(usrProfileMap.get(completelyRegistrationFlag))))
                .build();
        UsrProfile usrProfile = (UsrProfile) MessageUtil.buildMessageByMap(UsrProfile.getDescriptor(),UsrProfile.newBuilder(),usrProfileMap);
        LastLoginInfo lastLoginInfo;
        if (track == null) {
            lastLoginInfo = LastLoginInfo.newBuilder().build();
        } else {
            lastLoginInfo = LastLoginInfo.newBuilder()
                    .setLastLoginTime(track.getCreateTime())
                    .setDevice(String.format("%s(%s)", track.getDeviceModel(), track.getDeviceName()))
                    .setLastLoginIpAddr(track.getIp())
                    .build();
        }
        return Profile.newBuilder()
                .setUin(common.getUin())
                .setSecretKey(common.getSecret())
                .setUsrProfile(usrProfile)
                .setUsrStatusFlag(usrStatusFlag)
                .setLastLoginInfo(lastLoginInfo)
                .setProfileCompletion(profileCompletion)
                .setErrorCode(AccountConst.ERROR_CODE_SUCCESS)
                .build();
    }

    @Override
    public Integer resetPassword(ResetPassword resetPassword) {
        String secretPassword = MD5Utils.string2MD5WithSalt(resetPassword.getPassword(), passwordSalt);
        int error = accountDao.updateAccountPassword(resetPassword.getUin(),resetPassword.getEmail(),secretPassword);
        if(error == 1){
            return AccountConst.ERROR_CODE_SUCCESS;
        }else {
            return AccountConst.ERROR_CODE_NOT_EXISTS;
        }

    }
}
