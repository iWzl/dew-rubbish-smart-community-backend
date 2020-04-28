package com.upuphub.dew.community.push.service;

import com.upuphub.dew.community.connection.protobuf.push.SyncMachineSearchInfo;

public interface PushService {

    /**
     * 推送机器健康状态信息
     *
     * @param uin 用户uin
     * @param timestamp 查询的时间戳
     * @return 发送处理结果
     */
    int fireMachineHealthInfoWithUin(long uin, long timestamp);

    /**
     * 推送机器的查询信息历史
     *
     * @param syncMachineSearchInfo 需要携带的同步的信息
     * @return 推送的机器处理结果
     */
    int fireMachineSearch(SyncMachineSearchInfo syncMachineSearchInfo);
}