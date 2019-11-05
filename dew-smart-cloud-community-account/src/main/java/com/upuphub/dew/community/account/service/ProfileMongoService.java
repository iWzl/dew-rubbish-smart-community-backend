package com.upuphub.dew.community.account.service;

import com.upuphub.profile.annotation.ProfileLoader;
import com.upuphub.profile.annotation.ProfileParam;

import java.util.List;
import java.util.Map;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/10/15 20:56
 */
public interface ProfileMongoService {
    /**
     * 拉取Profile属性信息值
     *
     * @param uin Profile主键
     * @param profileKeys 需要查询的Key
     * @return 查询到的结果Key-Value
     */
    @ProfileLoader("pullProfile")
    Map<String,Object> pullProfile(@ProfileParam("uin") Long uin, @ProfileParam(needKeys = true) List<String> profileKeys);


    /**
     * 修改Profile信息
     *
     * @param uin uin
     * @param paramsMap 参数Map
     * @return 修改变化的参数属性
     */
    @ProfileLoader("pushProfile")
    Integer pushProfile(@ProfileParam("uin") Long uin, @ProfileParam(needMap = true) Map<String, Object> paramsMap);


    /**
     * 初始化Profile信息
     *
     * @param uin uin
     * @param paramsMap 参数Map
     * @return 修改变化的参数属性
     */
    @ProfileLoader("initProfile")
    Integer initProfile(@ProfileParam("uin") Long uin, @ProfileParam(needMap = true) Map<String, Object> paramsMap);


    /**
     * 判断用户Profile是否存在
     *
     * @param uin 用户Uin
     * @return 用户Uin的存在状态
     */
    @ProfileLoader(ignore = true)
    public boolean checkProfileByUin(long uin);


    /**
     * 清理Profile
     *
     * @param uin 需要清理的Profile信息
     */
    @ProfileLoader(ignore = true)
    public void cleanProfileByUin(Long uin);

}
