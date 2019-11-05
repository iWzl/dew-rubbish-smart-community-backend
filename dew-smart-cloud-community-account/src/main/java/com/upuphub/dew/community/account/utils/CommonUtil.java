package com.upuphub.dew.community.account.utils;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CommonUtil {

    /**
     * 通过计算用户Profile信息完成度
     *
     * @param originalMap 用户Profile信息
     * @return 用户信息完成度的计算结果
     */
    public static Integer calculateProfileValidData(Map<String, Object> originalMap) {
        AtomicInteger validDataCount = new AtomicInteger();
        if (originalMap == null || originalMap.size() == 0) return 0;
        originalMap.forEach((key, value) -> {
            if (!ObjectUtil.isEmpty(key) && !ObjectUtil.isEmpty(value) && !ObjectUtil.isEmpty(String.valueOf(value))) {
                validDataCount.getAndIncrement();
            }
        });
        return validDataCount.get();
    }
}
