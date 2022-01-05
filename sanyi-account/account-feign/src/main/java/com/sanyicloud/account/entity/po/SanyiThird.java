package com.sanyicloud.account.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sanyicloud.sanyi.common.core.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
    * yoyo 三方账号 — 可以是 设备号 或者 是三方账号的 唯一值 每个 三方账号对应一个 yoyo_account 账号 
 third_0 对应的 必定是 account_0
    */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SanyiThird implements Serializable {
    /**
    * 自增id
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
    * 账号id
    */
    @TableField(value = "account_id")
    private String accountId;

    /**
    * 第三方唯一编码
    */
    @TableField(value = "device_num")
    private String deviceNum;

    /**
    * 第三方类型 
    目前仅这三种，后续可以添加
    1 ios
    2 安卓
    3 其他
    */
    @TableField(value = "device_type")
    private Short deviceType;

    /**
    * 创建时间
    */
    @TableField(value = "create_time")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime createTime;

    /**
    * 更新时间
    */
    @TableField(value = "update_time")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", accountId=").append(accountId);
        sb.append(", deviceNum=").append(deviceNum);
        sb.append(", deviceType=").append(deviceType);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}