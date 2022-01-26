package com.sanyicloud.account.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

import java.io.Serializable;

/**
 * by zhaowenyuan create 2022/1/24 18:38
 * 主要用于 根据 推广码 获取 accountId
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SanyiAccountPromo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 账号id
     */
    @TableField(value = "account_id")
    private String accountId;
    /**
     * 账号的推广码 -- 同一个设备的 推广码 相同 -- 同一个设备只会有一个账号 -- 应当是一一对应的
     */
    @TableField(value = "promo_code")
    private String promoCode;

}
