package com.upuphub.dew.community.general.api.service.remote;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.relation.RelationPersistRequest;
import com.upuphub.dew.community.general.api.config.ProtoFeignConfiguration;
import com.upuphub.dew.community.general.api.service.remote.sentinel.DewMomentsSentinel;
import com.upuphub.dew.community.general.api.service.remote.sentinel.DewNewsSentinel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(qualifier = "dew-news",value = "dew-smart-community-news",configuration = ProtoFeignConfiguration.class,fallback = DewNewsSentinel.class)
public interface DewNewsService {

    @PostMapping(value = "/news/time",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    RpcResultCode fetchNewsWithNewCreateTimeBySyncKeyAndSize(@RequestBody RelationPersistRequest relationPersistRequest);
}
