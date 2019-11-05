package com.upuphub.dew.community.connection.constant;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/10/13 18:08
 */

public interface MqttConst {
    public static final String TOPIC_ACCOUNT = "DEW_ACCOUNT";
    public static final String TOPIC_RBC_API_SVT = "RBC_API_SVR";
    public static final String TOPIC_PUSH = "DEW_PUSH";

    public static final int TAG_RBC_API_EMAIL_CODE = 10000;
    public static final int TAG_ACCOUNT_PROFILE = 10001;


    public static final int TYPE_RBC_API_EMAIL_CODE = 10000;
    public static final int TYPE_ACCOUNT_PROFILE_MAP = 10001;

}
