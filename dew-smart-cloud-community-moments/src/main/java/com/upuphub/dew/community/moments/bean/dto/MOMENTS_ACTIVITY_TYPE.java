package com.upuphub.dew.community.moments.bean.dto;

public enum MOMENTS_ACTIVITY_TYPE {
    AT(1),
    REPLY(2),
    COMMENT(3),
    LIKED(4),
    FORWARD(5);


    private final int value;

    private MOMENTS_ACTIVITY_TYPE(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.name() + ":" + this.value;
    }

    public static MOMENTS_ACTIVITY_TYPE convert(int value) {
        for(MOMENTS_ACTIVITY_TYPE v : values()) {
            if(v.value() == value) {
                return v;
            }
        }
        return null;
    }
}
