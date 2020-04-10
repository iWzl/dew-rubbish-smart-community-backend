package com.upuphub.dew.community.operation.api.controller;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/11 00:19
 */

@RestController
@RequestMapping(value = "/oss/api/account",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "账户模块",tags = "账户相关模块")
public class AccountController {

}
