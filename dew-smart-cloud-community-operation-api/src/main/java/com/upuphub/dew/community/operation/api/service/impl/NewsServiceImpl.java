package com.upuphub.dew.community.operation.api.service.impl;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.protobuf.news.NewsDetailsResult;
import com.upuphub.dew.community.connection.protobuf.news.NewsDetailsResults;
import com.upuphub.dew.community.connection.protobuf.news.NewsSyncWithPageSizeRequest;
import com.upuphub.dew.community.operation.api.bean.vo.resp.NewsDetailsResp;
import com.upuphub.dew.community.operation.api.service.AccountService;
import com.upuphub.dew.community.operation.api.service.NewsService;
import com.upuphub.dew.community.operation.api.service.remote.DewNewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Resource
    private DewNewsService newsService;
    @Resource
    private AccountService accountService;

    @Override
    public NewsDetailsResp fetchNewsWithNewCreateTimeBySyncKeyAndSize(Long syncKey, Integer pageSize) {
        NewsDetailsResp newsDetailsResp = new NewsDetailsResp();
        NewsSyncWithPageSizeRequest newsSyncWithPageSizeRequest = NewsSyncWithPageSizeRequest
                .newBuilder()
                .setSyncKey(syncKey)
                .setPageSize(pageSize)
                .build();
        NewsDetailsResults newsDetailsResults = newsService.fetchNewsWithNewCreateTimeBySyncKeyAndSize(newsSyncWithPageSizeRequest);
        if(null != newsDetailsResults && newsDetailsResults.getRelationPersistResultsCount() != 0){
            List<NewsDetailsResp.NewsDetail> newsDetailList = new ArrayList<>(newsDetailsResults.getRelationPersistResultsCount());
            for (NewsDetailsResult newsDetailsResult : newsDetailsResults.getRelationPersistResultsList()) {
                NewsDetailsResp.NewsDetail newsDetail = MessageUtil.messageToCommonPojo(newsDetailsResult,NewsDetailsResp.NewsDetail.class);
                assert newsDetail != null;
                newsDetail.setAuthorProfile(accountService.pullSimpleProfileByUin(newsDetailsResult.getAuthorUin()));
                newsDetail.setNewsType(newsDetailsResult.getNewsTypeValue());
                newsDetail.setPayloadType(newsDetailsResult.getPayloadTypeValue());
                newsDetail.setFrontCoverImages(newsDetailsResult.getFrontCoverImagesList());
                newsDetailList.add(newsDetail);
            }
            newsDetailsResp.setNewsDetailList(newsDetailList);
        }
        return newsDetailsResp;
    }
}
