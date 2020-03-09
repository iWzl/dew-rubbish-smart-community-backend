package com.upuphub.dew.community.general.api.service.impl;


import com.upuphub.dew.community.general.api.bean.po.GarbageCategoriesPO;
import com.upuphub.dew.community.general.api.bean.po.GarbageClassSearchPO;
import com.upuphub.dew.community.general.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.general.api.bean.vo.resp.GarbageClassResp;
import com.upuphub.dew.community.general.api.dao.GarbageClassSearchDao;
import com.upuphub.dew.community.general.api.service.GarbageSearchService;
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

    @Override
    public ServiceResponseMessage searchRandomGarbageGameQuestion() {
        List<GarbageClassSearchPO> randomGarbageGameQuestions = garbageClassSearchDao.selectRandomGarbageGameQuestion();
        if(randomGarbageGameQuestions != null){
            // todo 没有VO 暂时直接处理
            return ServiceResponseMessage.createBySuccessCodeMessage(randomGarbageGameQuestions);
        }
        return ServiceResponseMessage.createBySuccessCodeMessage();
    }
}
