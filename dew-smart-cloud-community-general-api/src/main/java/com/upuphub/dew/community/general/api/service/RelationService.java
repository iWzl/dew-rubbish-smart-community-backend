package com.upuphub.dew.community.general.api.service;

import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.bean.vo.req.PersistRelationReq;

/**
 * @author LeoWang
 */
public interface RelationService {
    /**
     * 持久话用户的关系属性
     *
     * @param persistRelationReq 用户关系持久话请求
     * @return 持久化后的处理结果
     */
    ServiceResponseMessage persistRelation(PersistRelationReq persistRelationReq);

    /**
     * 查询用户的Match匹配上的好友关系
     *
     * @param uin 用户uin
     * @return 查询到的用户Match上的关系结果返回值
     */
    ServiceResponseMessage fetchPersistMatchRelation(Long uin);
}
