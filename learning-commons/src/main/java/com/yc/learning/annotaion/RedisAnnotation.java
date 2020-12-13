package com.yc.learning.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisAnnotation {
    boolean useRedis() default false;
    boolean deleteRedis() default false;
    boolean updateRedis() default false;
    String name() default "";
    String value() default  "";
}
