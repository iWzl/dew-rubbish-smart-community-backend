package com.upuphub.dew.community.relation.service;

import com.upuphub.dew.community.relation.bean.dto.RelationPersistResultDTO;
import com.upuphub.dew.community.relation.bean.dto.RelationRequestDTO;
import com.upuphub.dew.community.relation.bean.dto.RelationSearchDTO;

import java.util.List;

public interface RelationService {
    /**
     * 持久话用户好友关系
     *
     * @param relationRequest 关系持久话请求
     * @return 关系持久化的处理结果
     */
    int persistRelation(RelationRequestDTO relationRequest);

    /**
     * 查询用户的持久化好友关系
     *
     * @param relationSearch 用户持久化好友关系的查询条件
     * @return 用户指定持久话关系的信息列表
     */
    List<RelationPersistResultDTO> fetchRelationPersistResults(RelationSearchDTO relationSearch);

    /**
     * 查询用户的Match好友关系
     *
     * @param uin 用户uin
     * @return 用户的Match好友关系列表
     */
    List<RelationPersistResultDTO> fetchMatchRelationPersistResults(long uin);
}
