package com.upuphub.dew.community.general.api.bean.vo.common;

import com.upuphub.dew.community.connection.constant.AccountConst;
import com.upuphub.dew.community.general.api.utils.basic.ResultCodeEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理特殊的返回请求返回
 */
public class AccountResponseMessage{
    private static final Map<Integer,ServiceResponseMessage> accountMessageMap;
    static {
        accountMessageMap = new HashMap<>();
        accountMessageMap.put(AccountConst.ERROR_CODE_NOT_EXISTS,ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.USERNAME_OR_PASSWORD_ERROR.getCode(), "账号不存在或密码有误"));
        accountMessageMap.put(AccountConst.ERROR_CODE_NON_STANDARD_EMAIL,ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.EMAIL_VERIFY_ERROR.getCode(), "邮箱验证失败"));
        accountMessageMap.put(AccountConst.ERROR_CODE_ALREADY_EXISTS,ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.ACCOUNT_ALWAYS_EXISTS.getCode(), "账号已存在"));
        accountMessageMap.put(AccountConst.ERROR_CODE_COMMON_FAIL,ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.BAD_REQUEST.getCode(), "请求失败"));
    }

    public static ServiceResponseMessage getErrorServiceRespMsg(Integer accountConst){
        return accountMessageMap.get(accountConst);
    }
}
