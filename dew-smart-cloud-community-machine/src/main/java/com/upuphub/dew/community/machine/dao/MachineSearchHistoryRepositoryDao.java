package com.upuphub.dew.community.machine.dao;

import com.upuphub.dew.community.machine.bean.po.MachineSearchHistoryPO;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author LeoWang
 */
@Repository
public interface MachineSearchHistoryRepositoryDao extends PagingAndSortingRepository<MachineSearchHistoryPO, String> {

}
