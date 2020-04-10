package com.upuphub.dew.community.relation.controller;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.relation.*;
import com.upuphub.dew.community.relation.bean.dto.RelationPersistResultDTO;
import com.upuphub.dew.community.relation.bean.dto.RelationRequestDTO;
import com.upuphub.dew.community.relation.bean.dto.RelationSearchDTO;
import com.upuphub.dew.community.relation.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(consumes = "application/x-protobuf", produces = "application/x-protobuf")
public class RelationServiceController {
    @Autowired
    RelationService relationService;

    @PostMapping("/relation/persist")
    public RpcResultCode persistRelation(@RequestBody RelationPersistRequest relationPersistRequest) {
        RelationRequestDTO relationRequest = MessageUtil.messageToCommonPojo(relationPersistRequest, RelationRequestDTO.class);
        assert relationRequest != null;
        relationRequest.setRelationType(relationPersistRequest.getRelationTypeValue());
        relationRequest.setRelationSource(relationPersistRequest.getRelationSourceValue());
        int error = relationService.persistRelation(relationRequest);
        return RpcResultCode.newBuilder().setCode(error).build();
    }

    @PostMapping("/relation/search")
    public RelationPersistResults fetchRelationPersistResults(@RequestBody RelationSearchRequest relationSearchRequest) {
        RelationSearchDTO relationSearch = MessageUtil.messageToCommonPojo(relationSearchRequest, RelationSearchDTO.class);
        assert relationSearch != null;
        List<Integer> relationType = relationSearchRequest.getRelationTypesValueList();
        relationSearch.setRelationTypes(relationType);
        List<RelationPersistResultDTO> relationPersistResultList = relationService.fetchRelationPersistResults(relationSearch);
        return buildRelationPersistResults(relationPersistResultList);
    }


    @PostMapping("/relation/match")
    public RelationPersistResults fetchMatchRelationPersistResults(@RequestBody RelationSearchUinRequest relationSearchUinRequest) {
        List<RelationPersistResultDTO> relationPersistResultList = relationService.fetchMatchRelationPersistResults(relationSearchUinRequest.getUin());
        return buildRelationPersistResults(relationPersistResultList);
    }


    private RelationPersistResults buildRelationPersistResults(List<RelationPersistResultDTO> relationPersistResultList) {
        RelationPersistResults.Builder relationPersistResultsBuilder = RelationPersistResults.newBuilder();
        if (!relationPersistResultList.isEmpty()) {
            for (RelationPersistResultDTO relationResult : relationPersistResultList) {
                RelationPersistResult relationPersistResult = (RelationPersistResult) MessageUtil.buildMessageByBean(RelationPersistResult.getDescriptor(), RelationPersistResult.newBuilder(), relationResult);
                relationPersistResultsBuilder.addRelationPersistResults(relationPersistResult);
            }
        }
        return relationPersistResultsBuilder.build();
    }
}
