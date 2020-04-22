package com.upuphub.dew.community.machine.service.impl;

import com.upuphub.dew.community.connection.constant.MachineConst;
import com.upuphub.dew.community.connection.protobuf.machine.MachineSimpleInfoResult;
import com.upuphub.dew.community.machine.bean.dto.MachineBindDTO;
import com.upuphub.dew.community.machine.bean.dto.MachineHealthDTO;
import com.upuphub.dew.community.machine.bean.dto.MachineRegisterDTO;
import com.upuphub.dew.community.machine.bean.po.MachineHardwareDetailPO;
import com.upuphub.dew.community.machine.bean.po.MachineHealthInfoPO;
import com.upuphub.dew.community.machine.bean.po.MachineSearchHistoryPO;
import com.upuphub.dew.community.machine.dao.MachineHardwareDetailRepositoryDao;
import com.upuphub.dew.community.machine.dao.MachineHealthInfoRepositoryDao;
import com.upuphub.dew.community.machine.dao.MachineSearchHistoryRepositoryDao;
import com.upuphub.dew.community.machine.service.MachineService;
import com.upuphub.dew.community.machine.utils.DateUtil;
import com.upuphub.dew.community.machine.utils.ObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Optional;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/11 22:43
 */

@Service
public class MachineServiceImpl implements MachineService {

    @Resource
    MachineHardwareDetailRepositoryDao machineHardwareDetailRepositoryDao;
    @Resource
    MachineHealthInfoRepositoryDao machineHealthInfoRepositoryDao;
    @Resource
    MachineSearchHistoryRepositoryDao machineSearchHistoryRepositoryDao;

    @Override
    public int registerNewMachine(MachineRegisterDTO machineRegisterInfo) {
        if(ObjectUtil.isEmpty(machineRegisterInfo.getMachineMacAddress())){
            return MachineConst.ERROR_CODE_COMMON_FAIL;
        }
        if(machineHardwareDetailRepositoryDao.findById(machineRegisterInfo.getMachineMacAddress()).isPresent()){
            return MachineConst.ERROR_CODE_ALREADY_EXISTS;
        }else {
            MachineHardwareDetailPO machineHardwareDetail = new MachineHardwareDetailPO();
            BeanUtils.copyProperties(machineRegisterInfo,machineHardwareDetail);
            machineHardwareDetail.setRegisterTime(System.currentTimeMillis());
            machineHardwareDetailRepositoryDao.save(machineHardwareDetail);
            return MachineConst.ERROR_CODE_SUCCESS;
        }
    }

    @Override
    public MachineSimpleInfoResult fetchSimpleMachineInfoByMacAddress(String macAddress) {
        Optional<MachineHardwareDetailPO> machineHardwareDetail = machineHardwareDetailRepositoryDao.findById(macAddress);
        return machineHardwareDetail.map(machineHardwareInfo -> MachineSimpleInfoResult.newBuilder()
                .setBindUin(machineHardwareInfo.getBindUin() == null ? 0 : machineHardwareInfo.getBindUin())
                .setMachineMacAddress(machineHardwareInfo.getMachineMacAddress())
                .setMachineName(machineHardwareInfo.getMachineName())
                .build()).orElseGet(() -> MachineSimpleInfoResult.newBuilder().build());
    }

    @Override
    public int refreshMachineHealthInfo(MachineHealthDTO machineHealthInfo) {
        MachineHealthInfoPO machineHealthInfoEntity = new MachineHealthInfoPO();
        BeanUtils.copyProperties(machineHealthInfo,machineHealthInfoEntity);
        machineHealthInfoEntity.setRefreshTime(System.currentTimeMillis());
        machineHealthInfoRepositoryDao.save(machineHealthInfoEntity);
        return MachineConst.ERROR_CODE_SUCCESS;
    }

    @Override
    public int journalMachineSearchHistory(String macAddress, String searchKey) {
        String key = String.format("%s-%s", DateUtil.getTodayKey(),macAddress);
        Optional<MachineSearchHistoryPO> machineSearchHistory = machineSearchHistoryRepositoryDao.findById(key);
        if(machineSearchHistory.isPresent()){
            machineSearchHistory.get().getSearchKeyAndTimes().put(searchKey,
                    (machineSearchHistory.get().getSearchKeyAndTimes().getOrDefault(searchKey,0) + 1));
            machineSearchHistoryRepositoryDao.save(machineSearchHistory.get());
        }else {
            MachineSearchHistoryPO machineSearchHistoryDetail = new MachineSearchHistoryPO();
            machineSearchHistoryDetail.setKey(key);
            machineSearchHistoryDetail.setMachineAddress(macAddress);
            machineSearchHistoryDetail.setRecordTime( DateUtil.getTodayKey());
            machineSearchHistoryDetail.setRecordTimestamp(System.currentTimeMillis());
            machineSearchHistoryDetail.setSearchKeyAndTimes(Collections.singletonMap(searchKey,1));
            machineSearchHistoryRepositoryDao.save(machineSearchHistoryDetail);
        }
        return MachineConst.ERROR_CODE_SUCCESS;
    }

    @Override
    public int bindHardwareDevices(MachineBindDTO machineBindInfo) {
        if(ObjectUtil.isEmpty(machineBindInfo.getMachineMacAddress())
                || ObjectUtil.isEmpty(machineBindInfo.getBindKey())
                || ObjectUtil.isEmpty(machineBindInfo.getBindUin())){
            return MachineConst.ERROR_CODE_COMMON_FAIL;
        }
        Optional<MachineHardwareDetailPO> machineHardwareDetailOptional = machineHardwareDetailRepositoryDao.findById(machineBindInfo.getMachineMacAddress());
        if(!machineHardwareDetailOptional.isPresent()){
            return MachineConst.ERROR_CODE_NOT_EXISTS;
        }else if(!ObjectUtil.isEmpty(machineHardwareDetailOptional.get().getBindUin())){
            return MachineConst.ERROR_CODE_ALREADY_EXISTS;
        }else if(machineHardwareDetailOptional.get().getBindKey().equals(machineBindInfo.getBindKey())){
            MachineHardwareDetailPO machineHardwareDetail = machineHardwareDetailOptional.get();
            machineHardwareDetail.setBindUin(machineBindInfo.getBindUin());
            machineHardwareDetail.setBindTime(System.currentTimeMillis());
            machineHardwareDetail.setNikeName(machineBindInfo.getMachineName());
            machineHardwareDetailRepositoryDao.save(machineHardwareDetail);
            return MachineConst.ERROR_CODE_SUCCESS;
        }else {
                return MachineConst.ERROR_CODE_COMMON_FAIL;
        }
    }
}
