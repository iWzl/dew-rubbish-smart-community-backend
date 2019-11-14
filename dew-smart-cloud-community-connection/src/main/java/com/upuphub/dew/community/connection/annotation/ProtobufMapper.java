package com.upuphub.dew.community.connection.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ProtobufMapper {
    String value();
}
