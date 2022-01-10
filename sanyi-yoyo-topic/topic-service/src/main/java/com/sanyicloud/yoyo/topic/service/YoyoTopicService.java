package com.sanyicloud.yoyo.topic.service;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sanyicloud.sanyi.common.core.exception.ResultException;
import com.sanyicloud.yoyo.topic.entity.bo.TopicBO;
import com.sanyicloud.yoyo.topic.entity.enums.EnableStateEnum;
import com.sanyicloud.yoyo.topic.entity.po.YoyoTopic;
import org.apache.commons.lang3.ObjectUtils;

/**
 * by zhaowenyuan create 2022/1/6 12:01
 */
public interface YoyoTopicService extends IService<YoyoTopic> {
    LambdaQueryWrapper<YoyoTopic> queryMapper = new LambdaQueryWrapper<YoyoTopic>();

    default void editTopic(Integer topicId,
                   TopicBO topicBO){
        String topicName = topicBO.getTopicName();
        // 不为 当前Id , 并且名字相同时
        long aLong = this.count(queryMapper
                                .ne(YoyoTopic::getId, topicId)
                                .eq(YoyoTopic::getTopicName, topicName)
                );
        // 有同名的 需要报异常
        if (aLong > 0){
            throw new ResultException("The current topic already exists");
        }

        checkTime(topicBO);

        YoyoTopic yoyoTopic = YoyoTopic.builder()
                .topicName(topicName)
                .topicCover(topicBO.getTopicCover())
                .topicRule(topicBO.getTopicRule())
                .compareEndTime(DateUtil.parseLocalDateTime(topicBO.getCompareEndTime()))
                .compareStartTime(DateUtil.parseLocalDateTime(topicBO.getCompareStartTime()))
                .endTime(DateUtil.parseLocalDateTime(topicBO.getEndTime()))
                .startTime(DateUtil.parseLocalDateTime(topicBO.getStartTime()))
                .uploadEndTime(DateUtil.parseLocalDateTime(topicBO.getUploadEndTime()))
                .uploadStartTime(DateUtil.parseLocalDateTime(topicBO.getUploadStartTime()))
                .build();

        // 则为 新增
        if (0 == topicId){

        }
        // 为修改
        else {
            YoyoTopic yoyoById = this.getById(topicId);
            if (ObjectUtils.isNotEmpty(yoyoById)){
                EnableStateEnum enableState = yoyoById.getEnableState();
                if (!EnableStateEnum.NOT_STARTED.equals(enableState)){
                    yoyoTopic.setId(topicId);
                    updateTopic(yoyoTopic);
                }
                throw new ResultException("无法修改此主题, 当前主题 " + enableState.getDesc());
            }
            throw new ResultException("当前主题不存在, 请确认主题的有效性");
        }
    }

    /**
     * 修改 主题赛
     * @param yoyoTopic 主题赛所需参数
     */
    default void updateTopic(YoyoTopic yoyoTopic){
        this.saveOrUpdate(yoyoTopic);
    }

    /**
     * 效验 topic 的 各个时间格式
     * todo 需要策划进行确定
     * @param topicBO
     */
    default void checkTime(TopicBO topicBO){
        // 比赛时间 --  endTime >= startTime
        String startTime = topicBO.getStartTime();
        String endTime = topicBO.getEndTime();
        // 上传时间  -- uploadStartTime >= startTime && uploadEndTime <= endTime
        String uploadStartTime = topicBO.getUploadStartTime();
        String uploadEndTime = topicBO.getUploadEndTime();
        // 评选时间  --
        // compareStartTime >= startTime && >= uploadStartTime && compareEndTime <= endTime
        // || compareStartTime >= uploadEndTime && compareEndTime <= endTime
        String compareStartTime = topicBO.getCompareStartTime();
        String compareEndTime = topicBO.getCompareEndTime();
    }
}
