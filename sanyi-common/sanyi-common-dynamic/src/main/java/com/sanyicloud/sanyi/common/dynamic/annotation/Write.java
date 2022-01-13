package com.sanyicloud.sanyi.common.dynamic.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 切换 写数据源注解 -- 最好在 dao 层进行使用。
 * 无法支持事务
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Write {
    String value() default "";
}
