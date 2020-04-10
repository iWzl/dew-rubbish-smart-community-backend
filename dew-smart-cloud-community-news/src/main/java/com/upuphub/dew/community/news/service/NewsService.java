package com.upuphub.dew.community.news.service;

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
}
