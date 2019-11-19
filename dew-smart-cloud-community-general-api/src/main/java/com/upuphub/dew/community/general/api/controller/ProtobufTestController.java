package com.upuphub.dew.community.general.api.controller;

import com.upuphub.dew.community.connection.protobuf.account.Location;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

/**
 * 瞬间动态模块得前端控制器
 *
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/6 21:27
 */
@RestController
@RequestMapping(value = "/api/test")
@Api(value = "ProtobufTest",tags = "测试API")
public class ProtobufTestController {

    @ApiOperation(value = "ProtobufTest")
    @ApiParam(name = "inputStream",required = true,value = "ProtobufTest")
    @PostMapping(value = "/protobuf/base64",consumes = "application/x-protobuf;charset=UTF-8",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage postProtobufBase64(InputStream inputStream) throws IOException {
        byte[] body = input2byte(inputStream);
        String base64 = new String(body, StandardCharsets.UTF_8);
        byte[] protoBytes = Base64.getDecoder().decode(base64);
        Location location = Location.parseFrom(protoBytes);
        return ServiceResponseMessage.createBySuccessCodeMessage(location.getCity()+location.getCountry()+location.getProvince()+location.getLatitude());
    }

    @ApiOperation(value = "ProtobufTest")
    @ApiParam(name = "inputStream",required = true,value = "ProtobufTest")
    @PostMapping(value = "/protobuf/byte",consumes = "application/x-protobuf;charset=UTF-8",produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ServiceResponseMessage postProtobufByte(InputStream inputStream) throws IOException {
        byte[] body = input2byte(inputStream);
        Location location = Location.parseFrom(body);
        return ServiceResponseMessage.createBySuccessCodeMessage(location.getCity()+location.getCountry()+location.getProvince()+location.getLatitude());
    }


    @ApiOperation(value = "ProtobufTest")
    @GetMapping(value = "/protobuf/byte")
    public void getMomentsDynamicContent(HttpServletResponse httpServletResponse) throws IOException {
        Location location = Location.newBuilder()
                .setCity("Chengdu")
                .setCountry("China")
                .setLatitude(200.11)
                .setLongitude(211.11)
                .setProvince("四川")
                .setUin(100000L)
                .setStreet("高新区")
                .build();
        httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-protobuf;charset=UTF-8");
        httpServletResponse.getOutputStream().write(location.toByteArray());
    }

    @ApiOperation(value = "ProtobufTest")
    @GetMapping(value = "/protobuf/base64")
    public void getProtobufTestBase64(HttpServletResponse httpServletResponse) throws IOException {
        Location location = Location.newBuilder()
                .setCity("Chengdu")
                .setCountry("China")
                .setLatitude(200.11)
                .setLongitude(211.11)
                .setProvince("四川")
                .setUin(100000L)
                .setStreet("高新区")
                .build();
        httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE, "application/x-protobuf;charset=UTF-8");
        httpServletResponse.getOutputStream().write(Base64.getEncoder().encode(location.toByteArray()));
    }

    public static  byte[] input2byte(InputStream inStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        return swapStream.toByteArray();
    }

    public static byte[] unCompress(InputStream in) {
        if (in == null) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            GZIPInputStream unGzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = unGzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }
}
