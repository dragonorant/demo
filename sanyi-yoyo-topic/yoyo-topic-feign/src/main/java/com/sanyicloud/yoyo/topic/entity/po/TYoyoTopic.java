package com.sanyicloud.yoyo.topic.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.sanyicloud.yoyo.topic.entity.enums.EnableStatusEnum;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
    * yoyo - 主题赛表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@TableName("t_yoyo_topic")
public class TYoyoTopic implements Serializable {
    /**
    * 主键Id
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
    * 主题赛名称
    */
    @TableField(value = "topic_name")
    private String topicName;

    /**
     * 主题赛封面
     */
    @TableField(value = "topic_cover")
    private String topicCover;

    /**
    * 主题赛规则
    */
    @TableField(value = "topic_rule")
    private String topicRule;

    /**
    * 主题赛开始时间
    */
    @TableField(value = "start_time")
    private LocalDateTime startTime;

    /**
    * 主题赛结束时间
    */
    @TableField(value = "end_time")
    private LocalDateTime endTime;

    /**
    * 创建时间
    */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
    * 修改时间
    */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
    * 删除状态 0 未删除 1 已删除
    */
    @TableLogic(value = "false",delval = "true")
    private Boolean delState;

    /**
     * 是否发布, true 已发布, false 未发布
     */
    @TableField(value = "release_state")
    private Boolean releaseState;

    /**
    * 主题赛状态
        0 已结束
        1 进行中
        2 未开始
     只有 未开始状态的 主题赛才可以进行修改
    */
    @TableField(value = "enable_status")
    private EnableStatusEnum enableStatus;

    /**
    * 上传开始时间 -- 必须大于等于开始时间 小于等于结束时间
    */
    @TableField(value = "upload_start_time")
    private LocalDateTime uploadStartTime;

    /**
    * 上传结束时间 -- 必须大于等于开始时间 小于等于结束时间
    */
    @TableField(value = "upload_end_time")
    private LocalDateTime uploadEndTime;

    /**
    * 评比开始时间 -- 必须大于等于开始时间 小于等于结束时间
    */
    @TableField(value = "compare_start_time")
    private LocalDateTime compareStartTime;

    /**
    * 评比结束时间 -- 必须大于等于开始时间 小于等于结束时间
    */
    @TableField(value = "compare_end_time")
    private LocalDateTime compareEndTime;

    private static final long serialVersionUID = 1L;

}