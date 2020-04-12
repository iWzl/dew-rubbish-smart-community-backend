package com.upuphub.dew.community.machine.dao;

import com.upuphub.dew.community.machine.bean.po.MachineHealthInfoPO;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author LeoWang
 */
@Repository
public interface MachineHealthInfoRepositoryDao extends PagingAndSortingRepository<MachineHealthInfoPO, String> {

}
