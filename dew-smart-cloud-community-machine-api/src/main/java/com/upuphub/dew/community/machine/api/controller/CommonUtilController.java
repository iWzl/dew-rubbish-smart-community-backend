package com.upuphub.dew.community.machine.api.controller;

import com.upuphub.dew.community.machine.api.dao.GarbageClassSearchDao;
import com.upuphub.dew.community.machine.api.utils.HttpUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CommonUtilController {

    @Autowired
    GarbageClassSearchDao garbageClassSearchDao;

    @ApiOperation(value = "追踪访问IP")
    @GetMapping(value = "/goto/baidu",consumes = MediaType.ALL_VALUE)
    public void trackAccessIp(HttpServletResponse response) throws IOException {
        response.sendRedirect("https://www.baidu.com/");
        garbageClassSearchDao.saveIpTrack(HttpUtil.getIpAddr());
    }
}
