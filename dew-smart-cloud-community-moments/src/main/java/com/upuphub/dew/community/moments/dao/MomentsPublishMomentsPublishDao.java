package com.upuphub.dew.community.moments.dao;

import com.upuphub.dew.community.moments.bean.po.MomentsPublishPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MomentsPublishMomentsPublishDao {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("record") MomentsPublishPO record);

    int insertSelective(@Param("record")MomentsPublishPO record);

    MomentsPublishPO selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("record")MomentsPublishPO record);

    int updateByPrimaryKey(@Param("record")MomentsPublishPO record);
}