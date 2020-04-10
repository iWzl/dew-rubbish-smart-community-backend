package com.upuphub.dew.community.news.controller;

import com.upuphub.dew.community.connection.protobuf.news.News;
import com.upuphub.dew.community.connection.protobuf.news.NewsDetailsResults;
import com.upuphub.dew.community.connection.protobuf.news.NewsSyncWithPageSizeRequest;
import com.upuphub.dew.community.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(consumes = "application/x-protobuf", produces = "application/x-protobuf")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PostMapping("/news/time")
    public NewsDetailsResults fetchNewsWithNewCreateTimeBySyncKeyAndSize(@RequestBody NewsSyncWithPageSizeRequest newsSyncWithPageSizeRequest) {
        return newsService.fetchNewsWithNewCreateTimeBySyncKeyAndSize(newsSyncWithPageSizeRequest.getSyncKey(),newsSyncWithPageSizeRequest.getPageSize());
    }
}
