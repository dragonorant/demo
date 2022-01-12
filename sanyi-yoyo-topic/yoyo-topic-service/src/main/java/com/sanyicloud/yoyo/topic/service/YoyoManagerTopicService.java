package com.sanyicloud.yoyo.topic.service;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sanyicloud.sanyi.common.core.exception.ResultException;
import com.sanyicloud.yoyo.topic.entity.bo.TopicBO;
import com.sanyicloud.yoyo.topic.entity.po.TYoyoTopic;
import org.apache.commons.lang3.ObjectUtils;

/**
 * by zhaowenyuan create 2022/1/6 12:01
 */
public interface YoyoManagerTopicService extends IService<TYoyoTopic> {
    LambdaQueryWrapper<TYoyoTopic> queryMapper = new LambdaQueryWrapper<TYoyoTopic>();

    default void editTopic(Integer topicId,
                   TopicBO topicBO){
        // 修改时 才进行判断
        if (topicId != 0){
            TYoyoTopic yoyoById = this.getById(topicId);
            if (ObjectUtils.isEmpty(yoyoById)){
                throw new ResultException("当前主题不存在, 请确认主题的有效性");
            }
            // 只有未发布的版本才可以进行 修改
            Boolean releaseState = yoyoById.getReleaseState();
            if (releaseState){
                throw new ResultException("无法修改此主题, 当前主题状态已发布");
            }
        }
        String topicName = topicBO.getTopicName();
        // 新增时, 不需要判断主题名称是否重复
//        // 不为 当前Id , 并且名字相同时,
//        long aLong = this.count(queryMapper
//                .ne(YoyoTopic::getId, topicId)
//                .eq(YoyoTopic::getTopicName, topicName)
//        );
//        // 有同名的 需要报异常
//        if (aLong > 0){
//            throw new ResultException("The current topic already exists");
//        }
        // 进行时间的效验
        checkTime(topicBO);

        TYoyoTopic tYoyoTopic = TYoyoTopic.builder()
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
            // 新增的都是未开始的
            saveTopic(tYoyoTopic);
        }
        // 为修改
        else {
            tYoyoTopic.setId(topicId);
            updateTopic(tYoyoTopic);
        }
    }
    /**
     * 新增主题赛
     * 此处可以 添加注解 用于后端日志控制
     */
    default void saveTopic(TYoyoTopic tYoyoTopic){
        this.saveOrUpdate(tYoyoTopic);
    }

    /**
     * 修改 主题赛
     * @param tYoyoTopic 主题赛所需参数
     */
    default void updateTopic(TYoyoTopic tYoyoTopic){
        this.saveOrUpdate(tYoyoTopic);
    }

    /**
     * 效验 topic 的 各个时间格式
     * todo 需要策划进行确定
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
