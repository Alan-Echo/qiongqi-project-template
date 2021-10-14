package com.qiongqi.aop;

import java.lang.annotation.*;

/**
 * 限制接口访问次数,单位时间:秒
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface limit {

    //检测时间
    long time() default 3600;

    //检测时间内总访问次数
    int count() default 1;

    long locked() default 3600;

    //过期时间
    long timeout() default 60;
}
