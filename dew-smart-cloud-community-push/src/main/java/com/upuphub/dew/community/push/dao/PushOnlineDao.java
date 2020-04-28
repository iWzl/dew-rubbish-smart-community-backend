package com.upuphub.dew.community.push.dao;

import com.upuphub.dew.community.push.bean.po.PushOnlinePO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PushOnlineDao {

    /**
     * 查询用户PushId
     *
     * @param uin 用户UIN
     * @return 用户的uin
     */
    Long selectPushOnlineUinWithUin(@Param("uin") long uin);

    /**
     * 查询用户PushId
     *
     * @param uin 用户uin
     * @return topicId
     */
    String selectPushOnlineClientIdByUin(@Param("uin") long uin);

    /**
     * 查询Push在线时间拉取
     *
     * @param uin 用户uin
     * @return 用户在线时间
     */
    PushOnlinePO selectPushOnlineDetailByUin(@Param("uin") long uin);

    /**
     * 刷新用户在线状态
     *
     * @param uin        用户uin
     * @param clientId   连接ID
     * @param notifyTime 通知上报时间
     */
    void updatePushOnlineInfo(@Param("uin") long uin, @Param("clientId") String clientId, @Param("notifyTime") long notifyTime);

    void insertNewPushOnline(@Param("uin") long uin, @Param("clientId") String clientId, @Param("notifyTime") long notifyTime);
}
