package com.upuphub.dew.community.moments.exception;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/1/31 15:09
 */
public class MomentNotExistException extends RuntimeException {
    public MomentNotExistException(String message) {
        super(message);
    }
}
