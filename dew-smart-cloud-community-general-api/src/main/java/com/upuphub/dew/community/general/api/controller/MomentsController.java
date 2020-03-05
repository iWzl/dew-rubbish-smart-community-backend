package com.upuphub.dew.community.general.api.controller;

import com.upuphub.dew.community.general.api.bean.vo.req.*;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.bean.vo.resp.MomentIdResp;
import com.upuphub.dew.community.general.api.service.MomentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 瞬间动态模块得前端控制器
 *
 * @author Leo Wang
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/moments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Moments模块", tags = "Moments模块")
public class MomentsController {
    @Autowired
    MomentsService momentsService;

    @ApiOperation(value = "上传用户动态信息")
    @ApiParam(name = "momentDynamicContent", required = true, value = "用户上传编辑文章正文")
    @PostMapping(value = "/draft", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage postMomentsDynamicContent(@RequestBody @Validated MomentDynamicContentReq momentDynamicContent) {
        return ServiceResponseMessage.createBySuccessCodeMessage(
                MomentIdResp.builder()
                        .momentId(
                                momentsService.postMomentDynamicContent(momentDynamicContent).getMomentId()
                        ).build());
    }

    @ApiOperation(value = "拉取用户动态草稿")
    @GetMapping(value = "/draft", consumes = MediaType.ALL_VALUE)
    public ServiceResponseMessage pullDraftMomentDynamicContent() {
        return ServiceResponseMessage.createBySuccessCodeMessage(
                momentsService.pullDraftMomentDynamicContent()
        );
    }

    @ApiOperation(value = "删除用户动态草稿")
    @DeleteMapping(value = "/draft", consumes = MediaType.ALL_VALUE)
    public ServiceResponseMessage deleteDraftMomentDynamicContent() {
        return momentsService.deleteDraftMomentDynamicContent();
    }

    @ApiOperation(value = "发布Moment")
    @PostMapping(value = "/publish", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage publishMomentContent(@RequestBody @Validated MomentsPublishReq momentsPublishReq) {
        return momentsService.publishMomentContent(momentsPublishReq);

    }

    @ApiOperation(value = "评论Moment")
    @PostMapping(value = "/comment", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage postMomentComment(@RequestBody @Validated MomentCommentReq momentCommentReq) {
        return ServiceResponseMessage.createBySuccessCodeMessage(
                momentsService.postMomentComment(momentCommentReq)
        );
    }

    @ApiOperation(value = "回复Moment评论")
    @PostMapping(value = "/comment/reply", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage postMomentCommentReply(@RequestBody @Validated MomentReplyReq momentReplyReq) {
        momentsService.postMomentCommentReply(momentReplyReq);
        return ServiceResponseMessage.createBySuccessCodeMessage();
    }

    @ApiOperation(value = "按地理位置分页拉取所有的Moment")
    @PostMapping(value = "/location/fetch", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage fetchMomentAndReplyDetailByLocationCond(@RequestBody @Validated MomentLocationFilterReq momentLocationFilterReq) {
        return momentsService.fetchMomentAndReplyDetailByLocationCond(momentLocationFilterReq);
    }


    @ApiOperation(value = "按uin分页拉取所有的Moment")
    @PostMapping(value = "/uin/fetch", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage fetchMomentAndReplyDetailByUinCond(@RequestBody @Validated MomentUinFilterReq momentUinFilterReq) {
        return momentsService.fetchMomentAndReplyDetailByUin(momentUinFilterReq);
    }


    @ApiOperation(value = "按topic分页拉取所有的Moment")
    @PostMapping(value = "/topic/fetch", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage fetchMomentAndReplyDetailByTopicCond(@RequestBody @Validated MomentTopicFilterReq momentTopicFilterReq) {
        return momentsService.fetchMomentAndReplyDetailByTopic(momentTopicFilterReq);
    }

    @ApiOperation(value = "按classify分页拉取所有的Moment")
    @PostMapping(value = "/classify/fetch", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage fetchMomentAndReplyDetailByClassifyCond(@RequestBody @Validated MomentClassifyFilterReq momentClassifyFilterReq) {
        return momentsService.fetchMomentAndReplyDetailByClassify(momentClassifyFilterReq);
    }


    @ApiOperation(value = "按SyncKey拉取用户相关联的Activity信息")
    @PostMapping(value = "/activity/fetch", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage fetchMomentActivityDetailBySyncKey(@RequestBody @Validated MomentActivitySyncReq momentLocationFilterReq) {
        return ServiceResponseMessage.createBySuccessCodeMessage();
    }
}
