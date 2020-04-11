package com.upuphub.dew.community.operation.api.service.remote;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.news.*;
import com.upuphub.dew.community.operation.api.config.ProtoFeignConfiguration;
import com.upuphub.dew.community.operation.api.service.remote.sentinel.DewNewsServiceSentinel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(qualifier = "dew-news",value = "dew-smart-community-news",configuration = ProtoFeignConfiguration.class,fallback = DewNewsServiceSentinel.class)
public interface DewNewsService {

    /**
     * 通过同步Key和页码查询NewsDetails
     *
     * @param newsSyncWithPageSizeRequest  拉取page请求
     * @return 查询到的数据结果集合
     */
    @PostMapping(value = "/news/time",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    NewsDetailsResults fetchNewsWithNewCreateTimeBySyncKeyAndSize(@RequestBody NewsSyncWithPageSizeRequest newsSyncWithPageSizeRequest);

    /**
     * 新建News信息
     *
     * @param newsDetailRequest 新建明细信息请求
     * @return 创建的处理结果
     */
    @PostMapping(value = "/news/new",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    RpcResultCode buildNewsDetail(@RequestBody NewsDetailRequest newsDetailRequest);

    /**
     * 修改News信息
     *
     * @param newsDetailModifyRequest 修改信息请求
     * @return 修改News的处理请求
     */
    @PostMapping(value = "/news/modify",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    RpcResultCode modifyNewsDetail(@RequestBody NewsDetailModifyRequest newsDetailModifyRequest);

    /**
     * 删除用户News请求
     *
     * @param newsIdRequest 需要删除的NewsId
     * @return 删除Moments的处理结果
     */
    @PostMapping(value = "/news/delete",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    RpcResultCode deleteNewsDetail(@RequestBody NewsIdRequest newsIdRequest);

    /**
     * 通过MomentsId查询News请求
     *
     * @param newsIdRequest 新的用户请求
     * @return 返回新的明细信息
     */
    @PostMapping(value = "/news/fetch/newsId",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    NewsDetailsResult fetchNewsDetailByNewsId(@RequestBody NewsIdRequest newsIdRequest);
}
