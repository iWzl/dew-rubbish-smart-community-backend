package com.upuphub.dew.community.account.service.impl;


import com.upuphub.dew.community.account.bean.dto.ACCOUNT_ID_TYPE;
import com.upuphub.dew.community.account.bean.dto.ACCOUNT_LOGIN_VERIFY_STATUS;
import com.upuphub.dew.community.account.bean.po.AccountLoginPO;
import com.upuphub.dew.community.account.bean.po.AccountTrackPO;
import com.upuphub.dew.community.account.bean.po.AccountUinPO;
import com.upuphub.dew.community.account.dao.AccountDao;
import com.upuphub.dew.community.account.service.ProfileMysqlService;
import com.upuphub.dew.community.account.utils.ObjectUtil;
import com.upuphub.profile.annotation.ProfileService;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/10/15 20:59
 */

@ProfileService("ProfileMysqlService")
public class ProfileMysqlServiceImpl implements ProfileMysqlService {
    @Resource
    AccountDao accountDao;

    @Override
    public Map<String, Object> fetchLoginStatus(Long uin) {
        List<AccountLoginPO> accountLoginList =  accountDao.selectAccountLoginRecordByUin(uin);
        Map<String,Object> fetchLoginMap = new HashMap<>();
        if(!ObjectUtil.isEmpty(accountLoginList)){
            for (AccountLoginPO loginInfo : accountLoginList) {
                if(ACCOUNT_ID_TYPE.EMAIL.name().equals(loginInfo.getIdType())){
                    fetchLoginMap.put("email",loginInfo.getLoginKey());
                    fetchLoginMap.put("flagEmailVerify", ACCOUNT_LOGIN_VERIFY_STATUS.ENABLE.name().equals(loginInfo.getStatus()));
                    break;
                }
            }
            return fetchLoginMap;
        }
        return Collections.emptyMap();
    }


    @Override
    public Map<String, Object> fetchUinStatus(Long uin) {
        AccountUinPO accountUin = accountDao.selectAccountUinByUin(uin);
        Map<String,Object> uinStatusMap = new HashMap<>();
        if(!ObjectUtil.isEmpty(accountUin)){
            uinStatusMap.put("registerIdType",accountUin.getIdType());
            uinStatusMap.put("registerId",accountUin.getId());
            uinStatusMap.put("uinStatus",accountUin.getStatus());
            uinStatusMap.put("uinPlatform",accountUin.getPlatform());
            uinStatusMap.put("uinType",accountUin.getType());
            return uinStatusMap;
        }
        return Collections.emptyMap();
    }

    @Override
    public Map<String, Object> fetchLastLoginInfo(Long uin) {
        AccountTrackPO accountTrack = accountDao.selectAccountLastedLoginInfoByUin(uin);
        Map<String,Object> accountTrackMap = new HashMap<>();
        if(!ObjectUtil.isEmpty(accountTrack)){
            accountTrackMap.put("lastDeviceImei",accountTrack.getImei());
            accountTrackMap.put("lastIp",accountTrack.getIp());
            accountTrackMap.put("lastAppVersion",accountTrack.getAppVersion());
            accountTrackMap.put("lastSystemVersion",accountTrack.getSystemVersion());
            accountTrackMap.put("lastDeviceModel",accountTrack.getDeviceModel());
            accountTrackMap.put("lastDeviceName",accountTrack.getDeviceName());
            accountTrackMap.put("lastLoginTime",accountTrack.getCreateTime());
            return accountTrackMap;
        }
        return Collections.emptyMap();
    }
}
