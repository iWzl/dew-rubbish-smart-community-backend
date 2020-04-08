package com.upuphub.dew.community.relation.bean.po;

import lombok.Data;

@Data
public class RelationDetailPO {
    /**
     * 用户关系发起者
     */
    private Long sponsor;

    /**
     * 用户关系接收者
     */
    private Long recipient;

    /**
     * 用户关系类型
     */
    private Integer relationType;

    /**
     * 关系产生渠道
     */
    private Integer relationSource;

    /**
     * 关系发送时间
     */
    private Long refreshTime;

    /**
     * 保留字段
     */
    private Integer status;
}

