package com.upuphub.dew.community.relation.service;

import com.upuphub.dew.community.relation.bean.dto.RelationRequestDTO;

public interface RelationService {
    int persistRelation(RelationRequestDTO relationRequest);
}
