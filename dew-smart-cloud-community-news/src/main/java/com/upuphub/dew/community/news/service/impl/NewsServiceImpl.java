package com.upuphub.dew.community.news.service.impl;

import com.upuphub.dew.community.connection.common.MessageUtil;
import com.upuphub.dew.community.connection.protobuf.news.NEWS_TYPE;
import com.upuphub.dew.community.connection.protobuf.news.NewsDetailsResult;
import com.upuphub.dew.community.connection.protobuf.news.NewsDetailsResults;
import com.upuphub.dew.community.news.bean.po.NewsDetailPO;
import com.upuphub.dew.community.news.dao.NewsRepositoryDao;
import com.upuphub.dew.community.news.service.NewsService;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Resource
    NewsRepositoryDao newsRepositoryDao;
    @Resource
    MongoTemplate mongoTemplate;

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
}
