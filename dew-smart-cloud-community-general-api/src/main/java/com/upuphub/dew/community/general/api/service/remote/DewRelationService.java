package com.upuphub.dew.community.general.api.service.remote;


import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.relation.RelationPersistRequest;
import com.upuphub.dew.community.connection.protobuf.relation.RelationPersistResults;
import com.upuphub.dew.community.connection.protobuf.relation.RelationSearchRequest;
import com.upuphub.dew.community.connection.protobuf.relation.RelationSearchUinRequest;
import com.upuphub.dew.community.general.api.config.ProtoFeignConfiguration;
import com.upuphub.dew.community.general.api.service.remote.sentinel.DewRelationServiceSentinel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "dew-smart-community-relation",configuration = ProtoFeignConfiguration.class,fallback = DewRelationServiceSentinel.class)
public interface DewRelationService {

    /**
     * 持久化存储用户关系
     *
     * @param relationPersistRequest 持久化存储用户的关系请求
     * @return 持久化用户关系后的处理结果
     */
    @PostMapping(value = "/relation/persist",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    RpcResultCode persistRelation(@RequestBody RelationPersistRequest relationPersistRequest);

    /**
     * 查询用户指定关系类型的用户信息列表
     *
     * @param relationSearchRequest 关系检署请求需要的查询属性
     * @return 查询到的用户好友关系结果
     */
    @PostMapping(value = "/relation/search",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    RelationPersistResults fetchRelationPersistResults(@RequestBody RelationSearchRequest relationSearchRequest);

    /**
     * 查询用户Match上的用户关系属性实现
     *
     * @param relationSearchUinRequest 用户的Match查询需要的用户uin
     * @return 查询到的用户结果
     */
    @PostMapping(value = "/relation/match",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    RelationPersistResults fetchMatchRelationPersistResults(@RequestBody RelationSearchUinRequest relationSearchUinRequest);
}
