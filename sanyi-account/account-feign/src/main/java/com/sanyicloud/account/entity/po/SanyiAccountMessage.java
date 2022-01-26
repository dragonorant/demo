package com.sanyicloud.account.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sanyicloud.sanyi.common.mybatis.base.BaseEntity;
import lombok.*;

/**
 * by zhaowenyuan create 2022/1/26 16:49
 * 站内信 表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SanyiAccountMessage extends BaseEntity {
    /**
     * 消息id
     */
    @TableId(value = "message_id", type = IdType.AUTO)
    private Long messageId;
    /**
     * 账号id
     */
    @TableField(value = "account_id")
    private String accountId;
    /**
     * 站内信详情
     */
    @TableField(value = "message_info")
    private String messageInfo;
    /**
     * 站内信 读取状态 默认 false 未读取 / true 已读取
     */
    @TableField(value = "message_state")
    private Boolean messageState;
    /**
     * 站内信类型
     */
    @TableField(value = "message_type")
    private Short messageType;

}
