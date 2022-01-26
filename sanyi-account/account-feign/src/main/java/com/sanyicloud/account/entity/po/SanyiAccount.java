package com.sanyicloud.account.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sanyicloud.sanyi.common.mybatis.base.BaseEntity;
import lombok.*;

/**
    * yoyo 账号表 — 目前仅支持第三方账号的 创建 — 后期可以考虑加入 邮箱 或者 手机号的创建 
— 因处于国外，所以 最好是使用邮箱—推特-facebook-等账号
一个 yoyo_account 账号 可对应多个 yoyo_third 账号 — 但是目前仅支持1v1 ，因为是从 yoyo_third 中的 device_num 以及 device_type 进行创建的 account_id
account_0 对应的 必定是 third_0
    */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SanyiAccount extends BaseEntity {

    /**
    * 根据 设备号计算出来的 账号id
    */
    @TableId(value = "account_id", type = IdType.INPUT)
    private String accountId;

    /**
    * 昵称
    */
    @TableField(value = "nickname")
    private String nickname;

    /**
    * 头像
    */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 推广人数
     */
    @TableField(value = "invite_num")
    private Integer inviteNum;
}