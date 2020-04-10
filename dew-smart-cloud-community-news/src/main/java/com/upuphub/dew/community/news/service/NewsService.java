package com.upuphub.dew.community.news.service;

import com.upuphub.dew.community.connection.protobuf.news.NewsDetailModifyRequest;
import com.upuphub.dew.community.connection.protobuf.news.NewsDetailRequest;
import com.upuphub.dew.community.connection.protobuf.news.NewsDetailsResult;
import com.upuphub.dew.community.connection.protobuf.news.NewsDetailsResults;

public interface NewsService {
    /**
     * 通过创建时间和同步Key查询News信息
     *
     * @param syncKey 同步的Key
     * @param pageSize 分页熟悉
     * @return 查询到的处理结果
     */
    NewsDetailsResults fetchNewsWithNewCreateTimeBySyncKeyAndSize(long syncKey, int pageSize);

    /**
     * 创建信息的News信息
     *
     * @param newsDetailRequest News新闻信息的创建请求
     * @return 创建的处理结果
     */
    int buildNewsDetail(NewsDetailRequest newsDetailRequest);

    /**
     * 修改NewsDetail信息
     *
     * @param newsDetailModifyRequest 修改News详细信息请求
     * @return 修改处理的结果
     */
    int modifyNewsDetail(NewsDetailModifyRequest newsDetailModifyRequest);

    /**
     * 删除News信息
     *
     * @param newsId NewsId
     * @return 删除的处理结果
     */
    int deleteNewsDetail(long newsId);

    /**
     * @param newsId News新的ID
     * @return 查询到的明细
     */
    NewsDetailsResult fetchNewsDetailByNewsId(long newsId);
}
