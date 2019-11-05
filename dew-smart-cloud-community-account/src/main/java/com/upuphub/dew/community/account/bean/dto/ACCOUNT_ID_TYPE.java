package com.upuphub.dew.community.account.bean.dto;

public enum ACCOUNT_ID_TYPE {
    EMAIL(0),
    PHONE(1),
    WEIBO(2),
    WECHAT(3),
    QQ(4),
    UNKNOWN(5);


    private final int value;

    private ACCOUNT_ID_TYPE(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.name() + ":" + this.value;
    }

    public static ACCOUNT_ID_TYPE convert(int value) {
        for(ACCOUNT_ID_TYPE v : values()) {
            if(v.value() == value) {
                return v;
            }
        }
        return null;
    }

}
