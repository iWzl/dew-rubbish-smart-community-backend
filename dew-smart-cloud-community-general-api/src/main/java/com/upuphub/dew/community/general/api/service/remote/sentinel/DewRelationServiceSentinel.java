package com.upuphub.dew.community.general.api.service.remote.sentinel;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.relation.RelationPersistRequest;
import com.upuphub.dew.community.connection.protobuf.relation.RelationPersistResults;
import com.upuphub.dew.community.connection.protobuf.relation.RelationSearchRequest;
import com.upuphub.dew.community.connection.protobuf.relation.RelationSearchUinRequest;
import com.upuphub.dew.community.general.api.service.remote.DewRelationService;
import org.springframework.stereotype.Component;

@Component
public class DewRelationServiceSentinel implements DewRelationService {

    @Override
    public RpcResultCode persistRelation(RelationPersistRequest relationPersistRequest) {
        return RpcResultCode.newBuilder().build();
    }

    @Override
    public RelationPersistResults fetchRelationPersistResults(RelationSearchRequest relationSearchRequest) {
        return null;
    }

    @Override
    public RelationPersistResults fetchMatchRelationPersistResults(RelationSearchUinRequest relationSearchUinRequest) {
        return null;
    }
}
