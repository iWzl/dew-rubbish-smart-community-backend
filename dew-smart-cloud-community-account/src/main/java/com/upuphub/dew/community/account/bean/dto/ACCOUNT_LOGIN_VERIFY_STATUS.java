package com.upuphub.dew.community.account.bean.dto;

public enum ACCOUNT_LOGIN_VERIFY_STATUS {
    ENABLE(0),
    NO_VERIFY(1),
    DISABLE(2);

    private final int value;

    private ACCOUNT_LOGIN_VERIFY_STATUS(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.name() + ":" + this.value;
    }

    public static ACCOUNT_LOGIN_VERIFY_STATUS convert(int value) {
        for(ACCOUNT_LOGIN_VERIFY_STATUS v : values()) {
            if(v.value() == value) {
                return v;
            }
        }
        return null;
    }
}
