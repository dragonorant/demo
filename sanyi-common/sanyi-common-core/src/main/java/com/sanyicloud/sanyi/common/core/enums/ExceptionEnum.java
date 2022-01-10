package com.sanyicloud.sanyi.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * by zhaowenyuan create 2022/1/6 18:00
 */
@Getter
@AllArgsConstructor
public enum ExceptionEnum {
    RESOURCE_EXIST(3404,"资源不存在"),
    APP_RESOURCE_EXIST(3404,"产品资源不存在"),
    PLATFORM_RESOURCE_EXIST(3404,"平台资源不存在"),
    CONVERT_ERROR(0,"json 转换错误"),
    ;

    private final Integer code;

    private final String eMsg;
}