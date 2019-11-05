package com.upuphub.dew.community.general.api.exception;


/**
 * 资源未找到异常
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/6 20:41
 */

public class SourceNotFoundException extends RuntimeException {
    public SourceNotFoundException(String message) {
        super(message);
    }
}
