package com.sanyicloud.account.entity.bo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * by zhaowenyuan create 2021/11/4 12:13
 */
@Data
public class AccountBO {
    /**
     * 设备编号
     */
    @NotBlank(message = "设备编号不能为空")
    private String deviceNum;
    /**
     * 设备类型
     */
    @NotNull(message = "设备类型不能为空")
    private Short deviceType;

    /**
     * 昵称
     */
    @NotNull(message = "昵称不能为空")
    private String nickname;

    /**
     * 头像不能为空
     */
    @NotNull(message = "头像编号不能为空")
    private String avatar;

}
