package com.upuphub.dew.community.moments.bean.dto;

public enum MOMENTS_COMMENT_TYPE {
    ERROR_TYPE(-1),
    COMMENT(0),
    FAVORITE(1);

    private final int value;

    private MOMENTS_COMMENT_TYPE(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.name() + ":" + this.value;
    }

    public static MOMENTS_COMMENT_TYPE convert(int value) {
        for(MOMENTS_COMMENT_TYPE v : values()) {
            if(v.value() == value) {
                return v;
            }
        }
        return null;
    }
}
