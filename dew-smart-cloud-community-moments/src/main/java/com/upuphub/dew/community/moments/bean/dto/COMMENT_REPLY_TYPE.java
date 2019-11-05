package com.upuphub.dew.community.moments.bean.dto;

public enum COMMENT_REPLY_TYPE {
    COMMENT(0),
    REPLY(1);

    private final int value;

    private COMMENT_REPLY_TYPE(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.name() + ":" + this.value;
    }

    public static COMMENT_REPLY_TYPE convert(int value) {
        for(COMMENT_REPLY_TYPE v : values()) {
            if(v.value() == value) {
                return v;
            }
        }
        return null;
    }
}
