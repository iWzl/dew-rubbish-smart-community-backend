package com.upuphub.dew.community.moments.bean.dto;

public enum MOMENTS_ERROR_CODE {
    SUCCESS(0),
    FAIL(1);

    private final int value;

    private MOMENTS_ERROR_CODE(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.name() + ":" + this.value;
    }

    public static MOMENTS_ERROR_CODE convert(int value) {
        for(MOMENTS_ERROR_CODE v : values()) {
            if(v.value() == value) {
                return v;
            }
        }
        return null;
    }
}
