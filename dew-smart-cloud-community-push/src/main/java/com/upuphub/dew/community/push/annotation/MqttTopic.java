package com.upuphub.dew.community.push.annotation;

import java.lang.annotation.*;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2020/3/1 16:25
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MqttTopic {
    String topic();
    int tag();
}
