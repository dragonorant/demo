package com.sanyicloud.account.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sanyicloud.sanyi.common.core.util.EnumUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * by zhaowenyuan create 2022/1/25 15:15
 */
@Getter
@AllArgsConstructor
public enum DeviceTypeEnum implements EnumUtils.CodeEnum, IEnum<Short> {
    IOS((short) 1, "ios"),
    ANDROID((short) 2,"android"),
    OTHER((short) 3, "other")
    ;

    /**
     *      1 ios
     *     2 安卓
     *     3 其他
     */
    @EnumValue
    private final Short code;

    @JsonValue
    private final String desc;

    @Override
    public Short getValue() {
        return this.code;
    }
}
