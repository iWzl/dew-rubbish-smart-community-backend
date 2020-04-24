package com.upuphub.dew.community.machine.service.impl;

import com.upuphub.dew.community.connection.constant.MachineConst;
import com.upuphub.dew.community.connection.protobuf.machine.*;
import com.upuphub.dew.community.machine.bean.dto.MachineBindDTO;
import com.upuphub.dew.community.machine.bean.dto.MachineHealthDTO;
import com.upuphub.dew.community.machine.bean.dto.MachineHistorySearchDTO;
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
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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
    @Resource
    MongoTemplate mongoTemplate;

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
        Optional<MachineHardwareDetailPO> machineHardwareDetailOptional = machineHardwareDetailRepositoryDao
                .findById(machineBindInfo.getMachineMacAddress());
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

    @Override
    public List<MachineHealthResult> fetchMachineInfoAndHealthByUin(Long uin) {
        if(ObjectUtil.isEmpty(uin)){
            return Collections.emptyList();
        }
        Query machineByUinQuery = new Query();
        machineByUinQuery.addCriteria(Criteria.where("bind_uin").is(uin));
        List<MachineHardwareDetailPO> machineHardwareDetailList = mongoTemplate.find(machineByUinQuery,MachineHardwareDetailPO.class);
        if(machineHardwareDetailList.isEmpty()){
            return Collections.emptyList();
        }
        List<MachineHealthResult> machineHealthResultList = new ArrayList<>(machineHardwareDetailList.size());
        Query machineHealthQuery = new Query();
        List<String> macAddressList = machineHardwareDetailList
                .stream().map(MachineHardwareDetailPO::getMachineMacAddress).collect(Collectors.toList());
        machineHealthQuery.addCriteria(Criteria.where("_id").in(macAddressList));
        List<MachineHealthInfoPO> machineHealthInfoList = mongoTemplate.find(machineHealthQuery,MachineHealthInfoPO.class);
        Map<String,MachineHealthInfoPO> mackAddressAndHealthInfo = machineHealthInfoList
                .stream().collect(Collectors.toMap(MachineHealthInfoPO::getMacAddress,(info)->info));
        for (MachineHardwareDetailPO machineHardwareDetail : machineHardwareDetailList) {
            MachineHealthInfoPO machineHealthInfo = mackAddressAndHealthInfo
                    .getOrDefault(machineHardwareDetail.getMachineMacAddress(),new MachineHealthInfoPO());
            HealthInfoResult healthInfoResult = HealthInfoResult.newBuilder()
                    .setCpuCoreCount(machineHealthInfo.getCpuCoreCount())
                    .setCpuTemperature(machineHealthInfo.getCpuTemperature())
                    .setCpuUsageRate(machineHealthInfo.getCpuUsageRate())
                    .setDiskUseRate(machineHealthInfo.getDiskUseRate())
                    .setFreeDiskSize(machineHealthInfo.getFreeDiskSize())
                    .setFreeMemorySize(machineHealthInfo.getFreeMemorySize())
                    .setHardDiskSize(machineHealthInfo.getHardDiskSize())
                    .setMemorySize(machineHealthInfo.getMemorySize())
                    .setSystemName(machineHealthInfo.getSystemName())
                    .setUsedHardDiskSize(machineHealthInfo.getUsedHardDiskSize())
                    .setUsedMemorySize(machineHealthInfo.getUsedMemorySize())
                    .setMacAddress(machineHealthInfo.getMacAddress())
                    .setIpAddr(machineHealthInfo.getIpAddr())
                    .build();
            MachineHealthResult machineHealthResult = MachineHealthResult.newBuilder()
                    .setMachineMacAddress(machineHardwareDetail.getMachineMacAddress())
                    .setMachineName(machineHardwareDetail.getMachineName())
                    .setNikeName(machineHardwareDetail.getNikeName())
                    .setMachineType(machineHardwareDetail.getMachineType())
                    .setMachineVersion(machineHardwareDetail.getMachineVersion())
                    .setMachineMaker(machineHardwareDetail.getMachineMaker())
                    .setHealthInfoResult(healthInfoResult)
                    .build();
            machineHealthResultList.add(machineHealthResult);
        }
        return machineHealthResultList;
    }

    @Override
    public List<MachineSearchHistoryResult> fetchMachineSearchHistoryByUin(MachineHistorySearchDTO machineHistorySearch) {
        if(null == machineHistorySearch){
            return Collections.emptyList();
        }
        Query machineDetailQuery = new Query();
        boolean checkUin = false;
        if(!ObjectUtil.isEmpty(machineHistorySearch.getMachineMacAddress())){
            checkUin = true;
            machineDetailQuery.addCriteria(Criteria.where("_id").is(machineHistorySearch.getMachineMacAddress()));
        }else if(!ObjectUtil.isEmpty(machineHistorySearch.getUin())){
            machineDetailQuery.addCriteria(Criteria.where("bind_uin").is(machineHistorySearch.getUin()));
        }else {
            machineDetailQuery = null;
        }
        if(null != machineDetailQuery){
            List<MachineHardwareDetailPO> machineHardwareDetailList = mongoTemplate.find(machineDetailQuery,MachineHardwareDetailPO.class);
            if(checkUin && !ObjectUtil.isEmpty(machineHistorySearch.getUin()) && machineHardwareDetailList.size() == 1){
                if(machineHardwareDetailList.get(0).getBindUin().equals(machineHistorySearch.getUin())){
                    // 查询满足条件的 指定Mac地址的设备检测信息
                    List<MachineSearchHistoryPO> machineSearchHistoryList = fetchMachineSearchHistoryByDateRange(
                            machineHistorySearch.getStartTime(),machineHistorySearch.getEndTime(),Collections.singletonList(machineHistorySearch.getMachineMacAddress()));
                    return buildMachineSearchHistoryResult(machineHardwareDetailList,machineSearchHistoryList);
                }else {
                    return Collections.emptyList();
                }
            }else {
                List<String> macAddressList = machineHardwareDetailList.stream().map(MachineHardwareDetailPO::getMachineMacAddress)
                        .collect(Collectors.toList());
                // 查询满足条件的 指定Mac地址的设备检测信息
                List<MachineSearchHistoryPO> machineSearchHistoryList = fetchMachineSearchHistoryByDateRange(
                        machineHistorySearch.getStartTime(),machineHistorySearch.getEndTime(),macAddressList);
                return buildMachineSearchHistoryResult(machineHardwareDetailList,machineSearchHistoryList);
            }
        }else {
            // 查询所有的满足条件的设备搜索结果信息
            List<MachineSearchHistoryPO> machineSearchHistoryList = fetchMachineSearchHistoryByDateRange(
                    machineHistorySearch.getStartTime(),machineHistorySearch.getEndTime(),Collections.emptyList());
            List<String> machineAddressList = machineSearchHistoryList.stream().map(MachineSearchHistoryPO::getMachineAddress).collect(Collectors.toList());
            machineDetailQuery = new Query();
            machineDetailQuery.addCriteria(Criteria.where("_id").in(machineAddressList));
            List<MachineHardwareDetailPO> machineHardwareDetailList = mongoTemplate.find(machineDetailQuery,MachineHardwareDetailPO.class);
            return buildMachineSearchHistoryResult(machineHardwareDetailList,machineSearchHistoryList);
        }
    }


    private List<MachineSearchHistoryResult> buildMachineSearchHistoryResult(List<MachineHardwareDetailPO> machineHardwareDetailList,List<MachineSearchHistoryPO>  machineSearchHistoryList){
        List<MachineSearchHistoryResult> machineSearchHistoryResultList = new ArrayList<>(machineHardwareDetailList.size());
        Map<String,Map<String,Integer>> searchHistoryCountMap =new HashMap<>(machineHardwareDetailList.size());
        for (MachineSearchHistoryPO machineSearchHistory : machineSearchHistoryList) {
            if(searchHistoryCountMap.containsKey(machineSearchHistory.getMachineAddress())){
                for (String searchKey : machineSearchHistory.getSearchKeyAndTimes().keySet()) {
                    if(searchHistoryCountMap.get(machineSearchHistory.getMachineAddress()).containsKey(searchKey)){
                        searchHistoryCountMap.get(machineSearchHistory.getMachineAddress()).put(searchKey,
                                searchHistoryCountMap.get(machineSearchHistory.getMachineAddress()).get(searchKey)
                                        + machineSearchHistory.getSearchKeyAndTimes().get(searchKey)
                                );
                    }else {
                        searchHistoryCountMap.get(machineSearchHistory.getMachineAddress()).put(searchKey,machineSearchHistory.getSearchKeyAndTimes().get(searchKey));
                    }
                }
            }else {
                searchHistoryCountMap.put(machineSearchHistory.getMachineAddress(),machineSearchHistory.getSearchKeyAndTimes());
            }
        }
        for (MachineHardwareDetailPO machineHardwareDetail : machineHardwareDetailList) {

            Map<String,Integer> searchCountMap = searchHistoryCountMap.get(machineHardwareDetail.getMachineMacAddress());
            if(null == searchCountMap) {
                return Collections.emptyList();
            }
            List<MachineSearchCountResult> machineSearchCountResultList = new ArrayList<>(searchCountMap.size());
            searchCountMap.forEach((searchName,count)->{
                MachineSearchCountResult machineSearchCountResult = MachineSearchCountResult.newBuilder()
                        .setSearchName(searchName)
                        .setSearchCount(count)
                        .build();
                machineSearchCountResultList.add(machineSearchCountResult);
            });
            MachineSearchHistoryResult machineSearchHistoryResult = MachineSearchHistoryResult.newBuilder()
                    .setMachineMacAddress(machineHardwareDetail.getMachineMacAddress())
                    .setMachineName(machineHardwareDetail.getMachineName())
                    .setNikeName(machineHardwareDetail.getNikeName())
                    .setMachineType(machineHardwareDetail.getMachineType())
                    .setMachineVersion(machineHardwareDetail.getMachineVersion())
                    .setMachineMaker(machineHardwareDetail.getMachineMaker())
                    .addAllMachineSearchCountResultList(machineSearchCountResultList)
                    .build();
            machineSearchHistoryResultList.add(machineSearchHistoryResult);
        }
        return machineSearchHistoryResultList;
    }


    private List<MachineSearchHistoryPO> fetchMachineSearchHistoryByDateRange(Long startTime,Long endTime,List<String> macAddressList){
        Query machineHistoryQuery =  new Query();
        machineHistoryQuery.addCriteria(Criteria.where("record_timestamp").lte(endTime)
                .andOperator(Criteria.where("record_timestamp").gte(startTime)));
        if(!macAddressList.isEmpty()){
            machineHistoryQuery.addCriteria(Criteria.where("machine_address").in(macAddressList));
        }
        return mongoTemplate.find(machineHistoryQuery,MachineSearchHistoryPO.class);
    }
}
