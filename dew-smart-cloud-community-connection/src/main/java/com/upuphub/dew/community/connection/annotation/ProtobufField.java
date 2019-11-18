package com.upuphub.dew.community.connection.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ProtobufField {
    String value() default "";
    boolean ignore() default false;
}
