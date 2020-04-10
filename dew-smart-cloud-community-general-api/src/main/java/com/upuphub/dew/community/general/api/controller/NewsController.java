package com.upuphub.dew.community.general.api.controller;

import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.bean.vo.resp.NewsDetailsResp;
import com.upuphub.dew.community.general.api.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    private NewsService newsService;

    @ApiOperation(value = "拉取用户的News信息")
    @GetMapping(consumes = MediaType.ALL_VALUE)
    public ServiceResponseMessage fetchNewsWithNewCreateTimeBySyncKeyAndSize(@RequestParam("syncKey") Long syncKey, @RequestParam("pageSize") Integer pageSize) {
        NewsDetailsResp newsDetailsResp = newsService.fetchNewsWithNewCreateTimeBySyncKeyAndSize(syncKey,pageSize);
        return ServiceResponseMessage.createBySuccessCodeMessage(newsDetailsResp);
    }
}
