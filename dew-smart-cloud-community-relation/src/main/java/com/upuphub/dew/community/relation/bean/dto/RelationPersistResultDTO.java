package com.upuphub.dew.community.relation.bean.dto;

import lombok.Data;

@Data
public class RelationPersistResultDTO {
    private Long sponsor;
    private Long recipient;
    private Integer relationType;
    private Integer relationSource ;
    private Long refreshTime ;
}
