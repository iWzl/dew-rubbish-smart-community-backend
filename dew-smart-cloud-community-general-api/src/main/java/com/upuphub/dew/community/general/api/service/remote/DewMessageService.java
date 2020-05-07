package com.upuphub.dew.community.general.api.service.remote;

import com.upuphub.dew.community.connection.protobuf.message.MessagePayload;
import com.upuphub.dew.community.connection.protobuf.message.MessagePayloads;
import com.upuphub.dew.community.connection.protobuf.message.MessageResultCode;
import com.upuphub.dew.community.connection.protobuf.message.MessageSearchByTimeLine;
import com.upuphub.dew.community.general.api.config.ProtoFeignConfiguration;
import com.upuphub.dew.community.general.api.service.remote.sentinel.DewMessageServiceSentinel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:39
 */

@FeignClient(value = "dew-smart-community-message",configuration = ProtoFeignConfiguration.class,fallback = DewMessageServiceSentinel.class)
public interface DewMessageService {

    @PostMapping(value = "/message/persist",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    MessageResultCode persistMessage(@RequestBody MessagePayload messagePayload);

    @PostMapping(value = "/message/timeline",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    MessagePayloads fetchMessagePayloadsByTimeLine(@RequestBody MessageSearchByTimeLine messageSearchByTimeLine);

}
