package com.upuphub.dew.community.general.api.controller;

import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.bean.vo.req.PersistRelationReq;
import com.upuphub.dew.community.general.api.bean.vo.req.UidReq;
import com.upuphub.dew.community.general.api.service.RelationService;
import com.upuphub.dew.community.general.api.shiro.JWTUtil;
import com.upuphub.dew.community.general.api.utils.HttpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/relation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Relation模块", tags = "Relation模块")
public class RelationController {

    @Autowired
    RelationService relationService;

    @ApiOperation(value = "持久话用户好友关系")
    @ApiParam(name = "Persist Relation", required = true, value = "持久话用户好友关系")
    @PostMapping(value = "/persist", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage persistRelation(@RequestBody @Validated PersistRelationReq persistRelationReq) {
        return relationService.persistRelation(persistRelationReq);
    }

    @ApiOperation(value = "拉取用户Match关系")
    @ApiParam(name = "Persist Relation", required = true, value = "持久话用户好友关系")
    @GetMapping(value = "/persist/match", consumes = MediaType.ALL_VALUE)
    public ServiceResponseMessage fetchPersistMatchRelation() {
        return relationService.fetchPersistMatchRelation(HttpUtil.getUserUin());
    }

}
