package com.upuphub.dew.community.operation.api.utils;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.protobuf.account.BaseProfile;
import com.upuphub.dew.community.connection.protobuf.account.Location;
import com.upuphub.dew.community.connection.protobuf.account.Profile;
import com.upuphub.dew.community.connection.protobuf.account.UsernameAndPassword;
import com.upuphub.dew.community.connection.protobuf.machine.MachineRegisterRequest;
import com.upuphub.dew.community.connection.protobuf.moments.*;
import com.upuphub.dew.community.connection.protobuf.news.NEWS_TYPE;
import com.upuphub.dew.community.connection.protobuf.news.NewsDetailModifyRequest;
import com.upuphub.dew.community.connection.protobuf.news.NewsDetailRequest;
import com.upuphub.dew.community.connection.protobuf.news.PAYLOAD_TYPE;
import com.upuphub.dew.community.connection.protobuf.relation.RELATION_SOURCE;
import com.upuphub.dew.community.connection.protobuf.relation.RELATION_TYPE;
import com.upuphub.dew.community.connection.protobuf.relation.RelationPersistRequest;
import com.upuphub.dew.community.operation.api.bean.vo.req.*;
import com.upuphub.dew.community.operation.api.bean.vo.resp.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/4 23:40
 */
public class EDSUtil {
    private static final String PRODUCT = "DEW_OSS";

    public static ProfileResp profile2ProfileResp(@NotNull Profile rpcProfile) {
        ProfileResp profile = new ProfileResp();
        profile.setUin(rpcProfile.getUin());
        profile.setOpenId(DewOpenIdUtil.generateOpenId(rpcProfile.getUin()));
        profile.setProfileCompletion(rpcProfile.getProfileCompletion());
        UsrProfileResp usrProfile = MessageUtil.messageToCommonPojo(rpcProfile.getUsrProfile(), UsrProfileResp.class);
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

    public static UsernameAndPassword toProtobufMessage(@NotNull UsernameAndPasswordReq usernameAndPasswordReq){
        // 转换请求数据
        return UsernameAndPassword.newBuilder()
                .setUsername(usernameAndPasswordReq.getUserName())
                .setPassword(usernameAndPasswordReq.getPassword())
                .setIdType(0)
                .setProduct(PRODUCT)
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

    public static BaseProfile toProtobufMessage(@NotNull NewProfileReq profileReq) {
        return BaseProfile.newBuilder()
                .setUin(HttpUtil.getUserUin())
                .setBirthday(profileReq.getBirthday())
                .setAvatar(profileReq.getAvatar())
                .setGender(profileReq.getGender())
                .setName(profileReq.getName())
                .setIdType(profileReq.getIdType())
                .build();
    }

    public static Location toProtobufMessage(@NotNull LocationReq locationReq) {
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

    public static MomentDynamicContent toProtobufMessage(@NotNull MomentDynamicContentReq momentDynamicContentReq) {
        return MomentDynamicContent.newBuilder()
                .setUin(HttpUtil.getUserUin())
                .setLatitude(momentDynamicContentReq.getLatitude())
                .setLongitude(momentDynamicContentReq.getLongitude())
                .setContent(momentDynamicContentReq.getContent())
                .addAllPictures(momentDynamicContentReq.getPictures())
                .setTitle(momentDynamicContentReq.getTitle())
                .setTopic(momentDynamicContentReq.getTopic())
                .setClassify(momentDynamicContentReq.getClassify())
                .build();
    }


    public static MomentCommentRequest toProtobufMessage(@NotNull MomentCommentReq momentCommentReq) {
        return MomentCommentRequest.newBuilder()
                .setCommentBy(HttpUtil.getUserUin())
                .setMomentId(momentCommentReq.getMomentId())
                .setContent(momentCommentReq.getContent())
                .setCommentType(toProtobufMomentCommentType(momentCommentReq.getCommentType()))
                .build();
    }

    public static MomentDynamicContentResp toHttpVoBean(@NotNull MomentDynamicContent momentDynamicContent) {
        MomentDynamicContentResp momentDynamicContentResp = new MomentDynamicContentResp();
        momentDynamicContentResp.setDynamicId(momentDynamicContent.getDynamicContentId());
        momentDynamicContentResp.setTopic(momentDynamicContent.getTopic());
        momentDynamicContentResp.setClassify(momentDynamicContent.getClassify());
        momentDynamicContentResp.setTitle(momentDynamicContent.getTitle());
        momentDynamicContentResp.setContent(momentDynamicContent.getContent());
        momentDynamicContentResp.setLatitude(momentDynamicContent.getLatitude());
        momentDynamicContentResp.setLongitude(momentDynamicContent.getLongitude());
        momentDynamicContentResp.setPictures(getPicsList(momentDynamicContent));
        momentDynamicContentResp.setUpdateTime(momentDynamicContent.getUpdateTime());
        return momentDynamicContentResp;
    }


    private static List<String> getPicsList(@NotNull MomentDynamicContent dynamicContent){
        List<String> picList = new ArrayList<>();
        for (int i = 0; i < dynamicContent.getPicturesCount() ; i++) {
            picList.add(dynamicContent.getPictures(i));
        }
        return picList;
    }

    public static MomentDynamicPublish toProtobufMessage(@NotNull MomentsPublishReq momentsPublishReq) {
        if(null == momentsPublishReq){
            return null;
        }
        return MomentDynamicPublish.newBuilder()
                .setDynamicId(momentsPublishReq.getMomentId())
                .setLatitude(momentsPublishReq.getLatitude())
                .setLongitude(momentsPublishReq.getLongitude())
                .setPublishType(toProtobufMomentsPublishType(momentsPublishReq.getPublishType()))
                .setPublishBy(HttpUtil.getUserUin())
                .build();
    }


    public static MomentReplyRequest toProtobufMessage(@NotNull MomentReplyReq momentReplyReq) {
        if(null == momentReplyReq){
            return null;
        }
        return MomentReplyRequest.newBuilder()
                .setCommentId(momentReplyReq.getCommentId())
                .setReplyBy(HttpUtil.getUserUin())
                .setContent(momentReplyReq.getContent())
                .build();
    }

    private static MOMENTS_DYNAMIC_PUBLISH_TYPE toProtobufMomentsPublishType(@NotNull MomentsPublishReq.PUBLISH_TYPE publishType){
        switch (publishType){
            case ORDINARY:
                return MOMENTS_DYNAMIC_PUBLISH_TYPE.ORDINARY;
            case ORIGINAL:
                return MOMENTS_DYNAMIC_PUBLISH_TYPE.ORIGINAL;
            case REPRINT:
                return MOMENTS_DYNAMIC_PUBLISH_TYPE.REPRINT;
            default:
                throw new RuntimeException("Error Moments Publish Type");
        }
    }

    private static MOMENTS_COMMENT_TYPE toProtobufMomentCommentType(@NotNull MomentCommentReq.COMMENT_TYPE commentType){
        switch (commentType){
            case FAVORITE:
                return MOMENTS_COMMENT_TYPE.FAVORITE;
            case REPLY:
                return MOMENTS_COMMENT_TYPE.REPLY;
            default:
                return MOMENTS_COMMENT_TYPE.ERROR_TYPE;
        }
    }

    public static MomentDetailsLocationRequest toProtobufMessage(@NotNull MomentLocationFilterReq momentLocationFilterReq) {
        return MomentDetailsLocationRequest.newBuilder()
                .setLatitude(momentLocationFilterReq.getLocationReq().getLatitude())
                .setLongitude(momentLocationFilterReq.getLocationReq().getLongitude())
                .setPageNum(momentLocationFilterReq.getPageParam().getPageNum())
                .setPageSize(momentLocationFilterReq.getPageParam().getPageSize())
                .build();
    }

    public static MomentDetailsUinRequest toProtobufMessage(@NotNull MomentUinFilterReq momentUinFilterReq) {
        Long uin = null;
        if(null == momentUinFilterReq.getUin() || 0 == momentUinFilterReq.getUin()){
            if(null == momentUinFilterReq.getOpenId() || "".equals(momentUinFilterReq.getOpenId())){
                uin = HttpUtil.getUserUin();
            }else {
                uin = DewOpenIdUtil.generateUin(momentUinFilterReq.getOpenId());
            }
        }else {
            uin = momentUinFilterReq.getUin();
        }
        return MomentDetailsUinRequest.newBuilder()
                .setUin(uin)
                .setPageNum(momentUinFilterReq.getPageParamRequest().getPageNum())
                .setPageSize(momentUinFilterReq.getPageParamRequest().getPageSize())
                .build();
    }

    public static MomentDetailsTopicRequest toProtobufMessage(@NotNull MomentTopicFilterReq momentTopicFilterReq) {
        return MomentDetailsTopicRequest.newBuilder()
                .setPageNum(momentTopicFilterReq.getPageParam().getPageNum())
                .setTopic(momentTopicFilterReq.getTopic())
                .setPageSize(momentTopicFilterReq.getPageParam().getPageSize())
                .build();
    }

    public static MomentDetailsClassifyRequest toProtobufMessage(@NotNull MomentClassifyFilterReq momentClassifyFilterReq) {
        return MomentDetailsClassifyRequest.newBuilder()
                .setPageNum(momentClassifyFilterReq.getPageParam().getPageNum())
                .setClassify(momentClassifyFilterReq.getClassify())
                .setPageSize(momentClassifyFilterReq.getPageParam().getPageSize())
                .build();
    }

    public static RelationPersistRequest toProtobufMessage(@NotNull PersistRelationReq persistRelationReq) {
        return RelationPersistRequest.newBuilder()
                .setSponsor(persistRelationReq.getSponsorUin())
                .setRecipient(persistRelationReq.getRecipientUin())
                .setRelationSource(RELATION_SOURCE.forNumber(persistRelationReq.getRelationSource().getType()))
                .setRelationType(RELATION_TYPE.forNumber(persistRelationReq.getRelationType().getType()))
                .build();
    }

    public static NewsDetailRequest toProtobufMessage(@NotNull NewsDetailReq newsDetailReq) {
        return NewsDetailRequest.newBuilder()
                .setTitle(newsDetailReq.getTitle())
                .setCategory(newsDetailReq.getCategory())
                .setCreateDate(System.currentTimeMillis())
                .setAuthorUin(HttpUtil.getUserUin())
                .setPayload(newsDetailReq.getPayload())
                .setPayloadTypeValue(newsDetailReq.getPayloadType())
                .setNewsTypeValue(newsDetailReq.getNewsType())
                .addAllFrontCoverImages(newsDetailReq.getFrontCoverImages())
                .build();
    }

    public static NewsDetailModifyRequest toProtobufMessage(@NotNull NewsDetailModifyReq newsDetailReq) {
        return NewsDetailModifyRequest.newBuilder()
                .setNewsId(newsDetailReq.getNewsId())
                .setCategory(newsDetailReq.getCategory())
                .setTitle(newsDetailReq.getTitle())
                .setPayload(newsDetailReq.getTitle())
                .addAllFrontCoverImages(newsDetailReq.getFrontCoverImages())
                .setNewsTypeValue(null != newsDetailReq.getNewsType() ? newsDetailReq.getNewsType() : NEWS_TYPE.UNDEFINED_VALUE)
                .build();
    }

    public static MachineRegisterRequest toProtobufMessage(@NotNull MachineRegisterReq machineRegisterReq) {
        return MachineRegisterRequest.newBuilder()
                .setBindKey(UUID.randomUUID().toString().substring(2,10))
                .setMachineName(machineRegisterReq.getMachineName())
                .setMachineMaker(machineRegisterReq.getMachineMaker())
                .setMachineType(machineRegisterReq.getMachineType())
                .setMachineMacAddress(machineRegisterReq.getMachineMacAddress())
                .setMachineVersion(machineRegisterReq.getMachineVersion())
                .build();
    }
}
