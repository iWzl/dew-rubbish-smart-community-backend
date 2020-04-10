package com.upuphub.dew.community.general.api.controller;


import com.upuphub.dew.community.connection.protobuf.relation.RelationPersistRequest;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.service.remote.DewNewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/news", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "News模块", tags = "News模块")
public class NewsController {

    @Autowired
    private DewNewsService dewNewsService;

    @ApiOperation(value = "拉取用户的News信息")
    @GetMapping(value = "/fetch", consumes = MediaType.ALL_VALUE)
    public ServiceResponseMessage fetchNewsWithNewCreateTimeBySyncKeyAndSize(@RequestParam("syncKey") Long syncKey, @RequestParam("pageSize") Integer pageSize) {
        return ServiceResponseMessage.createBySuccessCodeMessage(
                dewNewsService.fetchNewsWithNewCreateTimeBySyncKeyAndSize(RelationPersistRequest.newBuilder().setRelationTypeValue(19321321).build())
        );
    }
}
