package com.upuphub.dew.community.general.api.service.rpc;

import cc.itsc.rbc.api.config.ProtoFeignConfiguration;
import cc.itsc.rbc.api.service.rpc.hystrix.RpcPushServiceHystrix;
import cc.itsc.utils.protobuf.push.EmailAndCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:39
 */

@FeignClient(value = "push",configuration = ProtoFeignConfiguration.class,fallback = RpcPushServiceHystrix.class)
public interface RpcPushService {

    @PostMapping(value = "/code/send",consumes = "application/x-protobuf",produces = "application/x-protobuf")
    public void sendVerificationCodeMail(@RequestBody EmailAndCode emailAndCode);
}
