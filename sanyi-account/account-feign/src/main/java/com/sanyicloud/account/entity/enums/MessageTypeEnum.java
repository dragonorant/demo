package com.sanyicloud.account.entity.enums;

import com.sanyicloud.sanyi.common.core.util.EnumUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * by zhaowenyuan create 2022/1/26 17:01
 * 消息类型 后期可进行其他扩展, 比如 点击类消息、语音类消息、视频类消息 等
 */
@Getter
@AllArgsConstructor
public enum MessageTypeEnum implements EnumUtils.CodeEnum {
    // 系统消息
    SYSTEM((short) 0, "system message"),
    // 邀请消息
    INVITE((short) 1,"invite message"),

    ;
    private final Short code;

    private final String desc;

}
