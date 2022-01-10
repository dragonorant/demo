package com.sanyicloud.sanyi.common.core.util;

import lombok.experimental.UtilityClass;

/**
 * by zhaowenyuan create 2022/1/10 14:28
 * 枚举获取帮助类 , 要求 enum 类必须 实现 CodeEnum, 包含有 Code , 并且 Code 唯一
 */
@UtilityClass
public class EnumUtils {
    public static <T extends CodeEnum> T getByCode(Short code, Class<T> tClass) {
        for (T each : tClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }

    public interface CodeEnum {
        Short getCode();
    }
}


