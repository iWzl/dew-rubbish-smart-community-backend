package com.upuphub.dew.community.connection.protocol.mqtt;

public enum PUSH_MESSAGE_TYPE {
    SYNC_MESSAGE(101),
    SYNC_PROFILE(102),
    SYNC_PROFILE_STATUS(103),
    SYNC_PROFILE_INFO(104),

    PUSH_AD(201),
    PUSH_SYSTEM_NOTIFY(202),
    PUSH_DYNAMIC_MESSAGE(203),

    PROHIBITED_USE_KEEP_CONN(301),
    PROHIBITED_USE_FIN_CONN(302);
    private final int value;

    private PUSH_MESSAGE_TYPE(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.name() + ":" + this.value;
    }

    public static PUSH_MESSAGE_TYPE convert(int value) {
        for(PUSH_MESSAGE_TYPE v : values()) {
            if(v.value() == value) {
                return v;
            }
        }
        return null;
    }
}
