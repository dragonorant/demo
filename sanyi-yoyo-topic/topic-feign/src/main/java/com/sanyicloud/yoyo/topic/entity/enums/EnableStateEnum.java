package com.sanyicloud.yoyo.topic.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * by zhaowenyuan create 2022/1/6 14:19
 */
@Getter
@AllArgsConstructor
public enum EnableStateEnum implements IEnum<Short> {
    COMPLETE((short) 0,"已结束"),
    ONGOING((short) 1, "进行中"),
    NOT_STARTED((short) 2,"未开始"),
    ;
    @EnumValue
    private final Short type;

    @JsonValue
    private final String desc;

    @Override
    public Short getValue() {
        return this.type;
    }
}
