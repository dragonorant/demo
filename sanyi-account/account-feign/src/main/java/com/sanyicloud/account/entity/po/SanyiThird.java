package com.sanyicloud.account.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sanyicloud.account.entity.enums.DeviceTypeEnum;
import com.sanyicloud.sanyi.common.mybatis.base.BaseEntity;
import lombok.*;

/**
    * yoyo 三方账号 — 可以是 设备号 或者 是三方账号的 唯一值 每个 三方账号对应一个 yoyo_account 账号 
 third_0 对应的 必定是 account_0
    */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SanyiThird extends BaseEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField(value = "account_id")
    private String accountId;
    @TableField(value = "device_num")
    private String deviceNum;
    /**
     * 第三方类型
     * 目前仅这三种，后续可以添加
     * 1 ios
     * 2 安卓
     * 3 其他
     * {@link com.sanyicloud.account.entity.enums.DeviceTypeEnum}
     */
    @TableField(value = "device_type")
    private DeviceTypeEnum deviceType;
    @TableField(value = "promo_code")
    private String promoCode;
    @TableField(value = "account_source")
    private Short accountSource;

}