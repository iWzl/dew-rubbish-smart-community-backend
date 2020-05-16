package com.upuphub.dew.community.machine.api.dao;

import com.upuphub.dew.community.machine.api.bean.po.GarbageCategoriesPO;
import com.upuphub.dew.community.machine.api.bean.po.GarbageClassSearchPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GarbageClassSearchDao {

    List<GarbageClassSearchPO> selectGarbageClassByLikeSearchKey(@Param("searchKey") String searchKey);

    GarbageCategoriesPO selectGarbageCategoriesByNum(@Param("classNum") Integer classNum);

    void saveIpTrack(@Param("ipAddr") String ipAddr);
}
