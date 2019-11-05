package com.upuphub.dew.community.account.bean.dto;

public enum ACCOUNT_TYPE {
    NORMAL(0),
    GRAY(1),
    AUDITOR(2);

    private final int value;

    private ACCOUNT_TYPE(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.name() + ":" + this.value;
    }

    public static ACCOUNT_TYPE convert(int value) {
        for(ACCOUNT_TYPE v : values()) {
            if(v.value() == value) {
                return v;
            }
        }
        return null;
    }
}
