package com.upuphub.dew.community.operation.api.service.impl;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.news.*;
import com.upuphub.dew.community.operation.api.bean.vo.req.NewsDetailModifyReq;
import com.upuphub.dew.community.operation.api.bean.vo.req.NewsDetailReq;
import com.upuphub.dew.community.operation.api.bean.vo.resp.NewsDetailsResp;
import com.upuphub.dew.community.operation.api.service.AccountService;
import com.upuphub.dew.community.operation.api.service.NewsService;
import com.upuphub.dew.community.operation.api.service.remote.DewNewsService;
import com.upuphub.dew.community.operation.api.utils.EDSUtil;
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
        if (null != newsDetailsResults && newsDetailsResults.getRelationPersistResultsCount() != 0) {
            List<NewsDetailsResp.NewsDetail> newsDetailList = new ArrayList<>(newsDetailsResults.getRelationPersistResultsCount());
            for (NewsDetailsResult newsDetailsResult : newsDetailsResults.getRelationPersistResultsList()) {
                NewsDetailsResp.NewsDetail newsDetail = buildNewsDetailsRespNewsDetailByRpcResult(newsDetailsResult);
                newsDetailList.add(newsDetail);
            }
            newsDetailsResp.setNewsDetailList(newsDetailList);
        }
        return newsDetailsResp;
    }

    @Override
    public Integer pullNewsDetails(NewsDetailReq newsDetailReq) {
        NewsDetailRequest newsDetailRequest = EDSUtil.toProtobufMessage(newsDetailReq);
        RpcResultCode resultCode = newsService.buildNewsDetail(newsDetailRequest);
        return null != resultCode ? resultCode.getCode() : -1;
    }

    @Override
    public Integer deleteNewsDetailsByNewsId(Long newsId) {
        RpcResultCode resultCode = newsService.deleteNewsDetail(NewsIdRequest.newBuilder().setNewsId(newsId).build());
        return null != resultCode ? resultCode.getCode() : -1;
    }

    @Override
    public Integer pushNewsDetailsByNewsIdAndDetail(NewsDetailModifyReq newsDetailReq) {
        NewsDetailModifyRequest newsDetailModifyRequest = EDSUtil.toProtobufMessage(newsDetailReq);
        RpcResultCode resultCode = newsService.modifyNewsDetail(newsDetailModifyRequest);
        return null != resultCode ? resultCode.getCode() : -1;
    }

    @Override
    public NewsDetailsResp.NewsDetail fetchNewsDetailByNewsId(Long newsId) {
        NewsDetailsResult newsDetailsResult = newsService.fetchNewsDetailByNewsId(NewsIdRequest.newBuilder().setNewsId(newsId).build());
        if(null != newsDetailsResult){
            return buildNewsDetailsRespNewsDetailByRpcResult(newsDetailsResult);
        }else {
            return new NewsDetailsResp.NewsDetail();
        }
    }

    private NewsDetailsResp.NewsDetail buildNewsDetailsRespNewsDetailByRpcResult(NewsDetailsResult newsDetailsResult){
        NewsDetailsResp.NewsDetail newsDetail = MessageUtil.messageToCommonPojo(newsDetailsResult, NewsDetailsResp.NewsDetail.class);
        assert newsDetail != null;
        newsDetail.setAuthorProfile(accountService.pullSimpleProfileByUin(newsDetailsResult.getAuthorUin()));
        newsDetail.setNewsType(newsDetailsResult.getNewsTypeValue());
        newsDetail.setPayloadType(newsDetailsResult.getPayloadTypeValue());
        newsDetail.setFrontCoverImages(newsDetailsResult.getFrontCoverImagesList());
        return newsDetail;
    }
}
