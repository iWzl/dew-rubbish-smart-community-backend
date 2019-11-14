package com.upuphub.dew.community.general.api.utils;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.protobuf.account.BaseProfile;
import com.upuphub.dew.community.connection.protobuf.account.Location;
import com.upuphub.dew.community.connection.protobuf.account.Profile;
import com.upuphub.dew.community.connection.protobuf.account.UsernameAndPassword;
import com.upuphub.dew.community.general.api.bean.vo.req.LocationReq;
import com.upuphub.dew.community.general.api.bean.vo.req.NewProfileReq;
import com.upuphub.dew.community.general.api.bean.vo.req.UsernameAndPasswordReq;
import com.upuphub.dew.community.general.api.bean.vo.resp.*;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/4 23:40
 */
public class EDSUtil {
    public static ProfileResp profile2ProfileResp(Profile rpcProfile) {
        ProfileResp profile = new ProfileResp();
        profile.setUin(rpcProfile.getUin());
        profile.setOpenId(DewOpenIdUtil.generateOpenId(rpcProfile.getUin()));
        profile.setProfileCompletion(rpcProfile.getProfileCompletion());
        UsrProfileResp usrProfile = MessageUtil.messageToCommonPojo(rpcProfile.getUsrProfile(),UsrProfileResp.class);
        UsrStatusFlagResp usrStatusFlag = MessageUtil.messageToCommonPojo(rpcProfile.getUsrStatusFlag(),UsrStatusFlagResp.class);
        UsrSettingResp usrSetting = new UsrSettingResp();
        usrSetting.setNotification(true);
        usrSetting.setVoiceNotification(true);
        usrSetting.setTheme("default");
        LastLoginInfoResp lastLoginInfo = new LastLoginInfoResp();
        lastLoginInfo.setDevice(rpcProfile.getLastLoginInfo().getDevice());
        lastLoginInfo.setLastLoginTime(DateUtil.date2String(rpcProfile.getLastLoginInfo().getLastLoginTime()));
        profile.setUsrProfile(usrProfile);
        profile.setUsrStatusFlag(usrStatusFlag);
        profile.setUsrSetting(usrSetting);
        profile.setLastLoginInfo(lastLoginInfo);
        return profile;
    }

    public static UsernameAndPassword toProtobufMessage(UsernameAndPasswordReq usernameAndPasswordReq){
        // 转换请求数据
        return UsernameAndPassword.newBuilder()
                .setUsername(usernameAndPasswordReq.getUserName())
                .setPassword(usernameAndPasswordReq.getPassword())
                .setIdType(usernameAndPasswordReq.getIdType())
                .setDevice(UsernameAndPassword.Device.newBuilder()
                        .setImei(usernameAndPasswordReq.getDeviceInfo().getImei())
                        .setAppVersion(usernameAndPasswordReq.getDeviceInfo().getAppVersion())
                        .setOSVersion(usernameAndPasswordReq.getDeviceInfo().getSystemVersion())
                        .setSystemModel(usernameAndPasswordReq.getDeviceInfo().getSystemModel())
                        .setDevName(usernameAndPasswordReq.getDeviceInfo().getDevName())
                        .setPlatform(usernameAndPasswordReq.getDeviceInfo().getPlatform())
                        .setIp(HttpUtil.getIpAddr())
                        .build())
                .build();
    }

    public static BaseProfile toProtobufMessage(NewProfileReq profileReq) {
        return BaseProfile.newBuilder()
                .setUin(HttpUtil.getUserUin())
                .setBirthday(profileReq.getBirthday())
                .setAvatar(profileReq.getAvatar())
                .setGender(profileReq.getGender())
                .setName(profileReq.getName())
                .setIdType(profileReq.getIdType())
                .build();
    }

    public static Location toProtobufMessage(LocationReq locationReq) {
        return Location.newBuilder()
                .setStreet(locationReq.getStreet())
                .setDistrict(locationReq.getDistrict())
                .setCity(locationReq.getCity())
                .setProvince(locationReq.getProvince())
                .setCountry(locationReq.getCountry())
                .setLongitude(locationReq.getLongitude())
                .setLatitude(locationReq.getLatitude())
                .setUin(HttpUtil.getUserUin())
                .build();
    }
}
