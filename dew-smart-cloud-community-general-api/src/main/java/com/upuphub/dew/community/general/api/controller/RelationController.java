package com.upuphub.dew.community.general.api.controller;

import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.bean.vo.req.PersistRelationReq;
import com.upuphub.dew.community.general.api.service.RelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/relation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Relation模块", tags = "Relation模块")
public class RelationController {

    @Autowired
    RelationService relationService;


    @ApiOperation(value = "上传用户动态信息")
    @ApiParam(name = "Persist Relation", required = true, value = "持久话用户好友关系")
    @PostMapping(value = "/persist", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage persistRelation(@RequestBody @Validated PersistRelationReq persistRelationReq) {
        return relationService.persistRelation(persistRelationReq);
    }

}
