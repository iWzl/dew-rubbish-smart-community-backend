package com.upuphub.dew.community.general.api.service;



import com.upuphub.dew.community.general.api.bean.req.LocationReq;
import com.upuphub.dew.community.general.api.bean.req.NewProfileReq;
import com.upuphub.dew.community.general.api.bean.req.UsernameAndPasswordReq;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.bean.vo.resp.SimpleProfileResp;

import java.util.Map;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/6 21:20
 */
public interface AccountService {

    /**
     * 用户登录的服务层
     * @param usernameAndPassword  用户登录使用的账号和密码
     * @return 登录成功的返回,是一个对象
     */
    ServiceResponseMessage loginOrRegister(UsernameAndPasswordReq usernameAndPassword);

    /**
     * 通过OpenId拉取基本用户信息
     * @param openId 用户OpenID
     * @return 用户的基本数据信息
     */
    SimpleProfileResp pullSimpleProfileByOpenId(String openId);

    /**
     * 完善用户注册信息
     * @param profileReq 用户注册信息
     * @return 完善信息的处理结果
     */
    ServiceResponseMessage improveUserInformation(NewProfileReq profileReq);

    /**
     * 刷新用户Profile信息
     *
     * @return 用户的Profile信息的刷新结果
     */
    ServiceResponseMessage refreshUserProfile();

    /**
     * 执行用户注销
     */
    void logout();

    /**
     * 刷新用户Profile信息
     *
     * @param locationReq 用户的位置信息
     * @return 用户Profile信息的刷新状态
     */
    ServiceResponseMessage refreshLocation(LocationReq locationReq);

    /**
     * 重设用户账户密码
     *
     * @param email 用户邮箱
     * @param code 邮箱验证码
     * @param password 用户密码
     * @return 密码的修改成功状态
     */
    ServiceResponseMessage resetUserPassword(String email, String code, String password);

    /**
     * 修改用于Profile信息
     *
     * @param modifyMap 用户需要修改的Profile信息
     * @return 用户的Profile信息的修改结果
     */
    ServiceResponseMessage resetUserProfile(Map<String, String> modifyMap);
}
