package com.upuphub.dew.community.news.service.impl;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.constant.NewsConst;
import com.upuphub.dew.community.connection.protobuf.news.*;
import com.upuphub.dew.community.news.bean.po.NewsDetailPO;
import com.upuphub.dew.community.news.component.SnowflakeId;
import com.upuphub.dew.community.news.dao.NewsRepositoryDao;
import com.upuphub.dew.community.news.service.NewsService;
import com.upuphub.dew.community.news.utils.ObjectUtil;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    @Resource
    NewsRepositoryDao newsRepositoryDao;
    @Resource
    MongoTemplate mongoTemplate;
    @Resource
    SnowflakeId snowflakeId;

    @Override
    public NewsDetailsResults fetchNewsWithNewCreateTimeBySyncKeyAndSize(long syncKey, int pageSize) {
        NewsDetailsResults.Builder newsDetailsResultsBuilder = NewsDetailsResults.newBuilder();
        Query query = new Query();
        if (0 < syncKey) {
            query.addCriteria(Criteria.where("_id").lt(syncKey));
        }
        query.limit(pageSize);
        query.with(new Sort(Sort.Direction.DESC, "create_date", "news_type"));
        List<NewsDetailPO> newsDetailDatabaseList = mongoTemplate.find(query, NewsDetailPO.class);
        if(!newsDetailDatabaseList.isEmpty()){
            for (NewsDetailPO newsDetail: newsDetailDatabaseList) {
                NewsDetailsResult newsDetailsResult = NewsDetailsResult.newBuilder()
                        .setNewsId(newsDetail.getNewsId())
                        .setNewsTypeValue(newsDetail.getNewsType())
                        .setAuthorUin(newsDetail.getCreateBy())
                        .setCategory(newsDetail.getCategory())
                        .setPayloadTypeValue(newsDetail.getPayloadType())
                        .setPayload(newsDetail.getPayload())
                        .addAllFrontCoverImages(newsDetail.getFrontCoverImages())
                        .setCreateDate(newsDetail.getCreateDate())
                        .setTitle(newsDetail.getTitle())
                        .build();
                newsDetailsResultsBuilder.addRelationPersistResults(newsDetailsResult);
            }
        }
        return newsDetailsResultsBuilder.build();
    }

    @Override
    public int buildNewsDetail(NewsDetailRequest newsDetailRequest) {
        NewsDetailPO newsDetailDocument = new NewsDetailPO();
        newsDetailDocument.setNewsId(snowflakeId.nextId());
        newsDetailDocument.setTitle(newsDetailRequest.getTitle());
        newsDetailDocument.setCreateDate(newsDetailRequest.getCreateDate());
        newsDetailDocument.setCategory(newsDetailRequest.getCategory());
        newsDetailDocument.setCreateBy(newsDetailRequest.getAuthorUin());
        newsDetailDocument.setNewsType(newsDetailRequest.getNewsTypeValue());
        newsDetailDocument.setPayloadType(newsDetailRequest.getPayloadTypeValue());
        newsDetailDocument.setPayload(newsDetailRequest.getPayload());
        newsDetailDocument.setFrontCoverImages(newsDetailRequest.getFrontCoverImagesList());
        newsRepositoryDao.save(newsDetailDocument);
        return NewsConst.ERROR_CODE_SUCCESS;
    }

    @Override
    public int modifyNewsDetail(NewsDetailModifyRequest newsDetailModifyRequest) {
        Optional<NewsDetailPO> newsDetailDocument = newsRepositoryDao.findById(newsDetailModifyRequest.getNewsId());
        if(newsDetailDocument.isPresent()){
            if(!ObjectUtil.isEmpty(newsDetailModifyRequest.getTitle())){
                newsDetailDocument.get().setTitle(newsDetailModifyRequest.getTitle());
            }
            if(!ObjectUtil.isEmpty(newsDetailModifyRequest.getCategory())){
                newsDetailDocument.get().setCategory(newsDetailModifyRequest.getCategory());
            }
            if(!ObjectUtil.isEmpty(newsDetailModifyRequest.getPayload())){
                newsDetailDocument.get().setPayload(newsDetailModifyRequest.getPayload());
            }
            if(!ObjectUtil.isEmpty(newsDetailModifyRequest.getFrontCoverImagesList())){
                newsDetailDocument.get().setFrontCoverImages(newsDetailModifyRequest.getFrontCoverImagesList());
            }
            if(newsDetailModifyRequest.getNewsTypeValue() != NEWS_TYPE.UNDEFINED_VALUE){
                newsDetailDocument.get().setNewsType(newsDetailModifyRequest.getNewsTypeValue());
            }
            newsRepositoryDao.save(newsDetailDocument.get());
            return NewsConst.ERROR_CODE_SUCCESS;
        }else {
            return NewsConst.ERROR_CODE_NOT_EXISTS;
        }
    }

    @Override
    public int deleteNewsDetail(long newsId) {
        newsRepositoryDao.deleteById(newsId);
        return NewsConst.ERROR_CODE_SUCCESS;
    }


    @Override
    public NewsDetailsResult fetchNewsDetailByNewsId(long newsId) {
        Optional<NewsDetailPO> newsDetailDocument = newsRepositoryDao.findById(newsId);
        if(newsDetailDocument.isPresent()){
            NewsDetailPO newsDetail =  newsDetailDocument.get();
            return NewsDetailsResult.newBuilder()
                    .setNewsId(newsDetail.getNewsId())
                    .setNewsTypeValue(newsDetail.getNewsType())
                    .setAuthorUin(newsDetail.getCreateBy())
                    .setCategory(newsDetail.getCategory())
                    .setPayloadTypeValue(newsDetail.getPayloadType())
                    .setPayload(newsDetail.getPayload())
                    .addAllFrontCoverImages(newsDetail.getFrontCoverImages())
                    .setCreateDate(newsDetail.getCreateDate())
                    .setTitle(newsDetail.getTitle())
                    .build();

        }
        return NewsDetailsResult.newBuilder().build();
    }
}
