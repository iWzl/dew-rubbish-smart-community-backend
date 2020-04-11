package com.upuphub.dew.community.operation.api.service;

import com.upuphub.dew.community.operation.api.bean.vo.resp.NewsDetailsResp;

public interface NewsService {
    /**
     * 拉取News信息
     *
     * @param syncKey 拉取的分页同步Key
     * @param pageSize 每页的大小
     * @return 查询到的NewsDetail信息明细
     */
    NewsDetailsResp fetchNewsWithNewCreateTimeBySyncKeyAndSize(Long syncKey, Integer pageSize);
}
