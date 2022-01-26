package com.sanyicloud.account.entity.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * by zhaowenyuan create 2021/11/4 12:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AccountBO extends DeviceBO {
    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像不能为空
     */
    private String avatar;

}
