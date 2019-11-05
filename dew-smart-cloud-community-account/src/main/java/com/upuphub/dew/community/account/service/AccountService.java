package com.upuphub.dew.community.account.service;



import com.upuphub.dew.community.connection.protobuf.account.*;

import java.util.List;
import java.util.Map;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/18 21:10
 */
public interface AccountService {
    /**
     * 用户注册的实现
     * @param usernameAndPassword 用户名/密码/设备等基本信息
     * @return 注册成功过后的初始化人员信息
     */
    Profile registered(UsernameAndPassword usernameAndPassword);

    /**
     * 用户登录
     * @param usernameAndPassword 用户名/密码/设备等基本信息
     * @return 等率成功后的人员信息
     */
    Profile login(UsernameAndPassword usernameAndPassword);


    /**
     * 提交通用Profile信息
     * @param uin 用户的Uin
     * @param generalProfile 通用Profile的键值对
     * @return 处理增加后的状态
     */
    Integer pushGeneralProfile(Long uin, Map<String, Object> generalProfile);

    /**
     * 拉取指定属性的Profile
     * @param uin 用户uin
     * @param keys 查询使用到的Key
     * @return 查询得到的返回结果
     */
    Map<String, Object> pullGeneralProfile(long uin, List<String> keys);

    /**
     * 完善用户注册后的信息
     *
     * @param baseProfile 用户核心Profile信息
     * @return 处理的结果状态
     */
    Integer improveUserRegistration(BaseProfile baseProfile);

    /**
     * 刷新新的用户地理位置信息
     * @param location 用户地理位置
     * @return 处理的结果状态
     */
    Integer refreshLocation(Location location);

    /**
     * 重置账户的用户名和密码
     *
     * @param resetPassword 重置用户名和密码
     * @return 处理的结果状态
     */
    Integer resetPassword(ResetPassword resetPassword);
}
