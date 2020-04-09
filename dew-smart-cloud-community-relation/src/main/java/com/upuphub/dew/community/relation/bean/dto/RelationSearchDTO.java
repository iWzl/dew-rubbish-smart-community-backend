package com.upuphub.dew.community.relation.bean.dto;

import com.upuphub.dew.community.connection.annotation.ProtobufField;
import lombok.Data;

import java.util.List;

@Data
public class RelationSearchDTO {
    @ProtobufField
    private Long searchUin;
    @ProtobufField
    private boolean isReverse;
    @ProtobufField(ignore = true)
    private List<Integer> relationTypes;
}
