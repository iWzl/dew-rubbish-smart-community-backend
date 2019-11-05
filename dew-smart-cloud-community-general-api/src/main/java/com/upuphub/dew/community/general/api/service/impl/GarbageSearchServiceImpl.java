package com.upuphub.dew.community.general.api.service.impl;

import cc.itsc.rbc.api.bean.po.GarbageCategoriesPO;
import cc.itsc.rbc.api.bean.po.GarbageClassSearchPO;
import cc.itsc.rbc.api.bean.vo.common.ServiceResponseMessage;
import cc.itsc.rbc.api.bean.vo.resp.GarbageClassResp;
import cc.itsc.rbc.api.dao.GarbageClassSearchDao;
import cc.itsc.rbc.api.service.GarbageSearchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GarbageSearchServiceImpl implements GarbageSearchService {
    @Resource
    GarbageClassSearchDao garbageClassSearchDao;

    @Override
    public ServiceResponseMessage searchGarbageClassByKey(String searchKey) {
        List<GarbageClassSearchPO> garbageClassList = garbageClassSearchDao.selectGarbageClassByLikeSearchKey(searchKey);
        if(garbageClassList == null || garbageClassList.isEmpty()){
            return ServiceResponseMessage.createBySuccessCodeMessage(Collections.emptyList());
        }else {
            List<GarbageClassResp> garbageClassRespList = new ArrayList<>();
            for (GarbageClassSearchPO searchResult: garbageClassList) {
                GarbageClassResp garbageClassResp = new GarbageClassResp();
                garbageClassResp.setName(searchResult.getName());
                garbageClassResp.setSortId(searchResult.getSortId());
                garbageClassRespList.add(garbageClassResp);
            }
            return ServiceResponseMessage.createBySuccessCodeMessage(garbageClassRespList);
        }
    }

    @Override
    public ServiceResponseMessage searchGarbageCategoriesByNum(Integer classNum) {
        GarbageCategoriesPO garbageCategories = garbageClassSearchDao.selectGarbageCategoriesByNum(classNum);
        if(garbageCategories != null){
            // todo 没有VO 暂时直接处理
            return ServiceResponseMessage.createBySuccessCodeMessage(garbageCategories);
        }
        return ServiceResponseMessage.createBySuccessCodeMessage();
    }
}
