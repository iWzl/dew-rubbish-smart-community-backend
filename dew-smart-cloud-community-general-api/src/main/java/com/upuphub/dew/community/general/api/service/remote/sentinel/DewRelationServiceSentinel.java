package com.upuphub.dew.community.general.api.service.remote.sentinel;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.relation.RelationPersistRequest;
import com.upuphub.dew.community.general.api.service.remote.DewRelationService;
import org.springframework.stereotype.Component;

@Component
public class DewRelationServiceSentinel implements DewRelationService {

    @Override
    public RpcResultCode persistRelation(RelationPersistRequest relationPersistRequest) {
        return RpcResultCode.newBuilder().build();
    }
}
