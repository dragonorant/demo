package com.sanyicloud.yoyo.topic.entity.po;

import com.baomidou.mybatisplus.annotation.TableLogic;
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
public class TYoyoTopic implements Serializable {
    /**
    * 主键Id
    */
    private Integer id;

    /**
    * 主题赛名称
    */
    private String topicName;

    /**
     * 主题赛封面
     */
    private String topicCover;

    /**
    * 主题赛规则
    */
    private String topicRule;

    /**
    * 主题赛开始时间
    */
    private LocalDateTime startTime;

    /**
    * 主题赛结束时间
    */
    private LocalDateTime endTime;

    /**
    * 创建时间
    */
    private LocalDateTime createTime;

    /**
    * 修改时间
    */
    private LocalDateTime updateTime;

    /**
    * 删除状态 0 未删除 1 已删除
    */
    @TableLogic(value = "false",delval = "true")
    private Boolean delState;

    /**
     * 是否发布, true 已发布, false 未发布
     */
    private Boolean releaseState;

    /**
    * 主题赛状态
        0 已结束
        1 进行中
        2 未开始
     只有 未开始状态的 主题赛才可以进行修改
    */
    private EnableStatusEnum enableStatus;

    /**
    * 上传开始时间 -- 必须大于等于开始时间 小于等于结束时间
    */
    private LocalDateTime uploadStartTime;

    /**
    * 上传结束时间 -- 必须大于等于开始时间 小于等于结束时间
    */
    private LocalDateTime uploadEndTime;

    /**
    * 评比开始时间 -- 必须大于等于开始时间 小于等于结束时间
    */
    private LocalDateTime compareStartTime;

    /**
    * 评比结束时间 -- 必须大于等于开始时间 小于等于结束时间
    */
    private LocalDateTime compareEndTime;

    private static final long serialVersionUID = 1L;

}