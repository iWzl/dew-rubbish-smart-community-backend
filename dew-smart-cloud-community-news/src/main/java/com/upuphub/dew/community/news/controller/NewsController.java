package com.upuphub.dew.community.news.controller;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.relation.RelationPersistRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(consumes = "application/x-protobuf", produces = "application/x-protobuf")
public class NewsController {

    @PostMapping("/news/time")
    public RpcResultCode fetchNewsWithNewCreateTimeBySyncKeyAndSize(@RequestBody RelationPersistRequest relationPersistRequest) {
        System.out.println();
        return RpcResultCode.newBuilder().setCode(0).build();
    }
}
