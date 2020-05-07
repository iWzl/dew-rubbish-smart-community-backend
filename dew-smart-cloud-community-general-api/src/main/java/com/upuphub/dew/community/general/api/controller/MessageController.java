package com.upuphub.dew.community.general.api.controller;

import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.bean.vo.req.PersistMessageReq;
import com.upuphub.dew.community.general.api.bean.vo.req.PersistMessageSearchReq;
import com.upuphub.dew.community.general.api.bean.vo.req.PersistRelationReq;
import com.upuphub.dew.community.general.api.bean.vo.req.PersistRelationSearchReq;
import com.upuphub.dew.community.general.api.service.MessageService;
import com.upuphub.dew.community.general.api.service.RelationService;
import com.upuphub.dew.community.general.api.utils.HttpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/message", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Message模块", tags = "Message模块")
public class MessageController {

    @Autowired
    MessageService messageService;

    @ApiOperation(value = "持久话用户Message")
    @ApiParam(name = "Persist Message", required = true, value = "持久话用户消息")
    @PostMapping(value = "/persist", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage persistMessage(@RequestBody @Validated PersistMessageReq persistMessageReq) {
        return messageService.persistMessage(persistMessageReq.getReceiver(),persistMessageReq.getMessageType(),persistMessageReq.getContent());
    }

    @ApiOperation(value = "拉取用户Message消息")
    @ApiParam(name = "Persist Message Search", required = true, value = "拉取用户Message消息")
    @PostMapping(value = "/fetch", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage fetchPersistMessageByTimeLineId(@RequestBody @Validated PersistMessageSearchReq persistMessageSearchReq) {
       return messageService.fetchPersistMessageByTimeLineId(persistMessageSearchReq.getTimeLineId(),persistMessageSearchReq.getSequenceId(),
               persistMessageSearchReq.getSize(),persistMessageSearchReq.isForward());
    }
}
