package com.upuphub.dew.community.relation.controller;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.relation.RelationPersistRequest;
import com.upuphub.dew.community.connection.protobuf.relation.RelationPersistResults;
import com.upuphub.dew.community.connection.protobuf.relation.RelationSearchRequest;
import com.upuphub.dew.community.connection.protobuf.relation.RelationSearchUinRequest;
import com.upuphub.dew.community.relation.bean.dto.RelationRequestDTO;
import com.upuphub.dew.community.relation.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(consumes = "application/x-protobuf",produces = "application/x-protobuf")
public class RelationServiceController {
    @Autowired
    RelationService relationService;

    @PostMapping("/relation/persist")
    public RpcResultCode persistRelation(@RequestBody RelationPersistRequest relationPersistRequest){
        RelationRequestDTO relationRequest = MessageUtil.messageToCommonPojo(relationPersistRequest, RelationRequestDTO.class);
        assert relationRequest != null;
        relationRequest.setRelationType(relationPersistRequest.getRelationTypeValue());
        relationRequest.setRelationSource(relationPersistRequest.getRelationSourceValue());
        int error = relationService.persistRelation(relationRequest);
        return RpcResultCode.newBuilder().setCode(error).build();
    }

    @PostMapping("/relation/search")
    public RelationPersistResults fetchRelationPersistResults(@RequestBody RelationSearchRequest relationSearchRequest){
        return null;
    }

    @PostMapping("/relation/match")
    public RelationPersistResults fetchMatchRelationPersistResults(@RequestBody RelationSearchUinRequest relationSearchUinRequest){
        return null;
    }
}
