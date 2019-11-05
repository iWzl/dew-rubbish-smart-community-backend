package com.upuphub.dew.community.moments.bean.dto;

public enum COMMENT_CONTENT_TYPE {
    TXT(0),
    PIC(1);

    private final int value;

    private COMMENT_CONTENT_TYPE(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.name() + ":" + this.value;
    }

    public static COMMENT_CONTENT_TYPE convert(int value) {
        for(COMMENT_CONTENT_TYPE v : values()) {
            if(v.value() == value) {
                return v;
            }
        }
        return null;
    }
}
