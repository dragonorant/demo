package com.sanyicloud.account.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sanyicloud.sanyi.common.mybatis.base.BaseEntity;
import lombok.*;

/**
 * by zhaowenyuan create 2022/1/25 12:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SanyiAccountInvite extends BaseEntity {
    /**
     * 推广人id
     */
    @TableId(value = "invite_id", type = IdType.INPUT)
    private String inviteId;

    /**
     * 被推广者id
     */
    @TableField(value = "direct_inviter_id")
    private String directInviterId;

    /**
     * 邀请时间 -- 取 UTC+0 的 时区
     *
     */
    @TableField(value = "invite_time")
    private String inviteTime;
}
