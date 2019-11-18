package com.upuphub.dew.community.general.api.controller;

import com.upuphub.dew.community.general.api.bean.vo.req.MomentDynamicContentReq;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
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
 * @date 2019/8/6 21:27
 */
@RestController
@RequestMapping(value = "/api/moments",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "用户瞬间动态",tags = "瞬间动态模块")
public class MomentsController {
    @Autowired
    MomentsService momentsService;

    @ApiOperation(value = "上传用户动态信息")
    @ApiParam(name = "momentDynamicContent",required = true,value = "用户上传编辑文章正文")
    @PostMapping(value = "/draft",consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage postMomentsDynamicContent(@RequestBody @Validated MomentDynamicContentReq momentDynamicContent){
        return momentsService.postMomentDynamicContent(momentDynamicContent);
    }

    @ApiOperation(value = "拉取用户动态草稿")
    @GetMapping(value = "/draft",consumes =  MediaType.ALL_VALUE)
    public ServiceResponseMessage pullDraftMomentDynamicContent(){
        return ServiceResponseMessage.createBySuccessCodeMessage(
                momentsService.pullDraftMomentDynamicContent()
        );
    }

    @ApiOperation(value = "删除用户动态草稿")
    @DeleteMapping(value = "/draft",consumes =  MediaType.ALL_VALUE)
    public ServiceResponseMessage deleteDraftMomentDynamicContent(){
        return ServiceResponseMessage.createBySuccessCodeMessage(
                momentsService.deleteDraftMomentDynamicContent()
        );
    }
}
