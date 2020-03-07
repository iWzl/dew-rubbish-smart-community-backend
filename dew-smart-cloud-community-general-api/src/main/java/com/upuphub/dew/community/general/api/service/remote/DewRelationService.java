package com.upuphub.dew.community.general.api.service.remote;


import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.relation.RelationPersistRequest;
import com.upuphub.dew.community.general.api.config.ProtoFeignConfiguration;
import com.upuphub.dew.community.general.api.service.remote.sentinel.DewRelationServiceSentinel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "dew-smart-community-relation",configuration = ProtoFeignConfiguration.class,fallback = DewRelationServiceSentinel.class)
public interface DewRelationService {

    @PostMapping(value = "/relation/persist",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    RpcResultCode persistRelation(@RequestBody RelationPersistRequest relationPersistRequest);
}
