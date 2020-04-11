package com.upuphub.dew.community.machine.service.impl;

import com.upuphub.dew.community.connection.constant.MachineConst;
import com.upuphub.dew.community.machine.bean.dto.MachineRegisterDTO;
import com.upuphub.dew.community.machine.bean.po.MachineHardwareDetailPO;
import com.upuphub.dew.community.machine.dao.MachineHardwareDetailRepositoryDao;
import com.upuphub.dew.community.machine.service.MachineService;
import com.upuphub.dew.community.machine.utils.ObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/11 22:43
 */

@Service
public class MachineServiceImpl implements MachineService {

    @Resource
    MachineHardwareDetailRepositoryDao machineHardwareDetailRepositoryDao;

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
}
