package com.upuphub.dew.community.general.api.service.remote.sentinel;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.relation.RelationPersistRequest;
import com.upuphub.dew.community.general.api.service.remote.DewNewsService;
import org.springframework.stereotype.Component;

@Component
public class DewNewsSentinel implements DewNewsService {
    @Override
    public RpcResultCode fetchNewsWithNewCreateTimeBySyncKeyAndSize(RelationPersistRequest relationPersistRequest) {
        return null;
    }
}
