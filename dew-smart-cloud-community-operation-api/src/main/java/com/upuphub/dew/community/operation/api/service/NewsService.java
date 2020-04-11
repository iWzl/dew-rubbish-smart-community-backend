package com.upuphub.dew.community.operation.api.service;

import com.upuphub.dew.community.operation.api.bean.vo.req.NewsDetailModifyReq;
import com.upuphub.dew.community.operation.api.bean.vo.req.NewsDetailReq;
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

    /**
     * 添加新的News信息
     *
     * @param newsDetailReq News信息请求
     * @return News信息创建的处理结果
     */
    Integer pullNewsDetails(NewsDetailReq newsDetailReq);

    /**
     * 删除News相关信息
     *
     * @param newsId NewsId
     * @return 删除的处理状态
     */
    Integer deleteNewsDetailsByNewsId(Long newsId);

    /**
     * 修改News相关信息
     *
     * @param newsDetailReq News相关信息请求
     * @return 修改News请求的处理结果
     */
    Integer pushNewsDetailsByNewsIdAndDetail(NewsDetailModifyReq newsDetailReq);

    /**
     * 通过NewsId 查询News详细信息
     *
     * @param newsId NewsId
     * @return News的详细信息
     */
    NewsDetailsResp.NewsDetail fetchNewsDetailByNewsId(Long newsId);
}
