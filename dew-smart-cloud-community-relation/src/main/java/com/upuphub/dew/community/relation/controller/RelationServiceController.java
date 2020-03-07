package com.upuphub.dew.community.relation.controller;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.relation.RelationPersistRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(consumes = "application/x-protobuf",produces = "application/x-protobuf")
public class RelationServiceController {

    @PostMapping("/relation/persist")
    public RpcResultCode persistRelation(@RequestBody RelationPersistRequest relationPersistRequest){
        return RpcResultCode.newBuilder().build();
    }
}
