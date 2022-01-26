package com.sanyicloud.account.entity.enums;

import com.sanyicloud.sanyi.common.core.util.EnumUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * by zhaowenyuan create 2022/1/26 15:10
 */
@Getter
@AllArgsConstructor
public enum AccountSourceEnum implements EnumUtils.CodeEnum {
    // 邀请
    INVITE((short) 1,"invite"),
    // 自来
    COME_FROM((short) 2, "come_from"),
    // 广告
    AD((short) 3, "ad"),
    ;

    private final Short code;

    private final String desc;
}
