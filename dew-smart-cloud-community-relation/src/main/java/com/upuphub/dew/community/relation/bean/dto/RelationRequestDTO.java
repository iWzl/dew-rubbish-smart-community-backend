package com.upuphub.dew.community.relation.bean.dto;

import com.upuphub.dew.community.connection.annotation.ProtobufField;
import lombok.Data;

@Data
public class RelationRequestDTO {
    @ProtobufField
    private Long sponsor;
    @ProtobufField
    private Long recipient;
    @ProtobufField(ignore = true)
    private Integer relationType;
    @ProtobufField(ignore = true)
    private Integer relationSource;
}
