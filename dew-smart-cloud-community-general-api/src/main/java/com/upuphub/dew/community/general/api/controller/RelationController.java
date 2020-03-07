package com.upuphub.dew.community.general.api.controller;

import com.upuphub.dew.community.connection.protobuf.relation.RELATION_SOURCE;
import com.upuphub.dew.community.connection.protobuf.relation.RELATION_TYPE;
import com.upuphub.dew.community.connection.protobuf.relation.RelationPersistRequest;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.service.remote.DewRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/relation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Relation模块", tags = "Relation模块")
public class RelationController {

    @Autowired
    DewRelationService dewRelationService;


    @ApiOperation(value = "上传用户动态信息")
    @ApiParam(name = "momentDynamicContent", required = true, value = "用户上传编辑文章正文")
    @PostMapping(value = "/draft", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage postMomentsDynamicContent() {
        dewRelationService.persistRelation(RelationPersistRequest.newBuilder()
                .setRecipient(100000L)
                .setSponsor(200000L)
                .setRelationType(RELATION_TYPE.LIKE)
                .setRelationSource(RELATION_SOURCE.DEFAULT)
                .build());
        return ServiceResponseMessage.createBySuccessCodeMessage();
    }

}
