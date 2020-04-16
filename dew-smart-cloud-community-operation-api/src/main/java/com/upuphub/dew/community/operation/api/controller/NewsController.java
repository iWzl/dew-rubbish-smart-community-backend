package com.upuphub.dew.community.operation.api.controller;

import com.upuphub.dew.community.operation.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.operation.api.bean.vo.req.NewsDetailModifyReq;
import com.upuphub.dew.community.operation.api.bean.vo.req.NewsDetailReq;
import com.upuphub.dew.community.operation.api.bean.vo.resp.NewsDetailsResp;
import com.upuphub.dew.community.operation.api.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping(value = "/api/news", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "News模块", tags = "News模块")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @ApiOperation(value = "拉取用户的News信息")
    @GetMapping(consumes = MediaType.ALL_VALUE)
    public ServiceResponseMessage fetchNewsWithNewCreateTimeBySyncKeyAndSize(@RequestParam("syncKey") Long syncKey, @RequestParam("pageSize") Integer pageSize) {
        NewsDetailsResp newsDetailsResp = newsService.fetchNewsWithNewCreateTimeBySyncKeyAndSize(syncKey,pageSize);
        return ServiceResponseMessage.createBySuccessCodeMessage(newsDetailsResp);
    }


    @ApiOperation(value = "添加新的News信息")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage pullNewsDetails(@RequestBody @Validated NewsDetailReq newsDetailReq) {
        return ServiceResponseMessage.createBySuccessCodeMessage(
                newsService.pullNewsDetails(newsDetailReq)
        );
    }

    @ApiOperation(value = "删除News信息")
    @DeleteMapping(consumes = MediaType.ALL_VALUE)
    public ServiceResponseMessage deleteNewsDetailsByNewsId(@RequestParam("newsId") @Min(value = 10000,message = "NewsId有效") Long newsId) {
        return ServiceResponseMessage.createBySuccessCodeMessage(
                newsService.deleteNewsDetailsByNewsId(newsId)
        );
    }

    @ApiOperation(value = "修改News相关信息")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage pushNewsDetailsByNewsIdAndDetail(@RequestBody @Validated NewsDetailModifyReq newsDetailReq) {
        return ServiceResponseMessage.createBySuccessCodeMessage(
                newsService.pushNewsDetailsByNewsIdAndDetail(newsDetailReq)
        );
    }

    @ApiOperation(value = "拉取指定News的详细信息")
    @GetMapping(value = "/one",consumes = MediaType.ALL_VALUE)
    public ServiceResponseMessage fetchNewsDetailByNewsId(@RequestParam("newsId") Long newsId) {
        return ServiceResponseMessage.createBySuccessCodeMessage(
                newsService.fetchNewsDetailByNewsId(newsId)
        );
    }






}
