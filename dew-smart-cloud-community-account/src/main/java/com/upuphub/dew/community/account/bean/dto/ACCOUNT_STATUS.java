package com.upuphub.dew.community.account.bean.dto;

public enum ACCOUNT_STATUS {
    DELETED(0),
    DISABLED(1),
    ENABLE(2),
    DEACTIVATED(3);

    private final int value;

    private ACCOUNT_STATUS(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.name() + ":" + this.value;
    }

    public static ACCOUNT_STATUS convert(int value) {
        for(ACCOUNT_STATUS v : values()) {
            if(v.value() == value) {
                return v;
            }
        }
        return null;
    }
}
