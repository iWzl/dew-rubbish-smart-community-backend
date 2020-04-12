package com.upuphub.dew.community.machine.api.service.impl;

import com.upuphub.dew.community.machine.api.bean.po.GarbageCategoriesPO;
import com.upuphub.dew.community.machine.api.bean.po.GarbageClassSearchPO;
import com.upuphub.dew.community.machine.api.bean.vo.common.ServiceResponseMessage;
import com.upuphub.dew.community.machine.api.bean.vo.resp.GarbageClassResp;
import com.upuphub.dew.community.machine.api.dao.GarbageClassSearchDao;
import com.upuphub.dew.community.machine.api.service.GarbageSearchService;
import com.upuphub.dew.community.machine.api.service.MachineService;
import com.upuphub.dew.community.machine.api.utils.HttpUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/12 14:16
 */
@Service
public class GarbageSearchServiceImpl implements GarbageSearchService {

    @Resource
    GarbageClassSearchDao garbageClassSearchDao;
    @Resource
    MachineService machineService;

    @Override
    public ServiceResponseMessage searchGarbageClassByKey(String searchKey) {
        machineService.asyncTrackMachineSearchHistory(searchKey, HttpUtil.getMachineMacAddr());
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
