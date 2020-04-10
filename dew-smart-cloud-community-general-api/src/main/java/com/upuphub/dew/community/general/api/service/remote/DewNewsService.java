package com.upuphub.dew.community.general.api.service.remote;

import com.upuphub.dew.community.connection.protobuf.common.RpcResultCode;
import com.upuphub.dew.community.connection.protobuf.news.*;
import com.upuphub.dew.community.general.api.config.ProtoFeignConfiguration;
import com.upuphub.dew.community.general.api.service.remote.sentinel.DewNewsSentinel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(qualifier = "dew-news",value = "dew-smart-community-news",configuration = ProtoFeignConfiguration.class,fallback = DewNewsSentinel.class)
public interface DewNewsService {

    @PostMapping(value = "/news/time",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    NewsDetailsResults fetchNewsWithNewCreateTimeBySyncKeyAndSize(@RequestBody NewsSyncWithPageSizeRequest newsSyncWithPageSizeRequest);

    @PostMapping(value = "/news/new",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    RpcResultCode buildNewsDetail(@RequestBody NewsDetailRequest newsDetailRequest);

    @PostMapping(value = "/news/modify",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    RpcResultCode modifyNewsDetail(@RequestBody NewsDetailModifyRequest newsDetailModifyRequest);

    @PostMapping(value = "/news/delete",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    RpcResultCode deleteNewsDetail(@RequestBody NewsIdRequest newsIdRequest);

    @PostMapping(value = "/news/fetch/newsId",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    NewsDetailsResult fetchNewsDetailByNewsId(@RequestBody NewsIdRequest newsIdRequest);
}
