package com.upuphub.dew.community.general.api.dao;

import cc.itsc.rbc.api.bean.po.GarbageCategoriesPO;
import cc.itsc.rbc.api.bean.po.GarbageClassSearchPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GarbageClassSearchDao {

    public List<GarbageClassSearchPO> selectGarbageClassByLikeSearchKey(@Param("searchKey") String searchKey);

    public GarbageCategoriesPO selectGarbageCategoriesByNum(@Param("classNum") Integer classNum);
}
