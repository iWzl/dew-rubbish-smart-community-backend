package com.upuphub.dew.community.account.service;

import com.upuphub.profile.annotation.ProfileLoader;
import com.upuphub.profile.annotation.ProfileParam;

import java.util.Map;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/10/15 20:56
 */

public interface ProfileMysqlService {

    /**
     * 拉取用户的登录信息
     *
     * @param uin 用户Uin
     * @return 用户拉取到的信息返回
     */
    @ProfileLoader("fetchLoginStatus")
    Map<String, Object> fetchLoginStatus(@ProfileParam("uin") Long uin);

    /**
     * 拉取用户的Uin核心信息
     *
     * @param uin 用户Uin
     * @return 用户拉取到的信息返回
     */
    @ProfileLoader("fetchUinStatus")
    Map<String, Object> fetchUinStatus(@ProfileParam("uin") Long uin);

    /**
     * 拉取用户的最后登录记录
     *
     * @param uin 用户Uin
     * @return 用户拉取到的信息返回
     */
    @ProfileLoader("fetchLastLoginInfo")
    Map<String, Object> fetchLastLoginInfo(@ProfileParam("uin") Long uin);

}
