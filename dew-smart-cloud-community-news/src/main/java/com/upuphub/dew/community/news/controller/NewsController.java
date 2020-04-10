package com.upuphub.dew.community.news.controller;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.news.*;
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

    @PostMapping("/news/new")
    public RpcResultCode buildNewsDetail(@RequestBody NewsDetailRequest newsDetailRequest) {
        return RpcResultCode.newBuilder().setCode(
                newsService.buildNewsDetail(newsDetailRequest)
        ).build();
    }

    @PostMapping("/news/modify")
    public RpcResultCode modifyNewsDetail(@RequestBody NewsDetailModifyRequest newsDetailModifyRequest) {
        return RpcResultCode.newBuilder().setCode(
                newsService.modifyNewsDetail(newsDetailModifyRequest)
        ).build();
    }

    @PostMapping("/news/delete")
    public RpcResultCode deleteNewsDetail(@RequestBody NewsIdRequest newsIdRequest) {
        return RpcResultCode.newBuilder().setCode(
                newsService.deleteNewsDetail(newsIdRequest.getNewsId())
        ).build();
    }

    @PostMapping("/news/fetch/newsId")
    public NewsDetailsResult fetchNewsDetailByNewsId(@RequestBody NewsIdRequest newsIdRequest) {
        return newsService.fetchNewsDetailByNewsId(newsIdRequest.getNewsId());
    }


}
