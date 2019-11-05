package com.upuphub.dew.community.account.service.impl;

import com.upuphub.dew.community.account.service.ProfileTransferService;
import com.upuphub.dew.community.account.utils.DateTimeUtil;
import com.upuphub.profile.annotation.ProfileService;

import java.util.Collections;
import java.util.Map;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/10/15 21:00
 */

@ProfileService("ProfileTransferService")
public class ProfileTransferServiceImpl implements ProfileTransferService {

    private static final String BIRTHDAY_MATCH = "[0-9]+";

    /**
     * 生日转换为年龄的方法实现
     * @param birthday 用户生日的时间戳
     * @return 计算后的用户年龄
     */
    @Override
    public Map<String, Object> birthToAge(String birthday){
        if(null != birthday && birthday.trim().matches(BIRTHDAY_MATCH)) {
            return  Collections.singletonMap("age", DateTimeUtil.birthToAge(Long.parseLong(birthday)));
        }else {
            return  Collections.singletonMap("age",Integer.MIN_VALUE);
        }
    }
}
