package com.upuphub.dew.community.machine.api.service.impl;

import com.upuphub.dew.community.machine.api.bean.vo.req.MachineHealthReq;
import com.upuphub.dew.community.machine.api.service.MachineService;
import org.springframework.stereotype.Service;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/4/12 13:18
 */

@Service
public class MachineServiceImpl implements MachineService {
    @Override
    public int hardwareHealthMonitoring(MachineHealthReq machineHealthReq) {

        return 0;
    }
}
