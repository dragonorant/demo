package com.sanyicloud.yoyo.topic.controller;

import com.sanyicloud.sanyi.common.core.util.Result;
import com.sanyicloud.yoyo.topic.entity.bo.TopicBO;
import com.sanyicloud.yoyo.topic.service.YoyoManagerTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * by zhaowenyuan create 2022/1/10 16:58
 * 后端接口代码
 */
@RestController
public class TopicManagerController {

    @Autowired
    YoyoManagerTopicService yoyoManagerTopicService;

    /**
     * todo -- 需要 添加 主题赛奖励信息的 创建
     * @param topicId 主题赛id
     * @param topicBO 主题赛信息
     */
    @PostMapping(value = "/{topicId}")
    public Result editTopic(@PathVariable Integer topicId,
                            @Valid TopicBO topicBO)
    {
        yoyoManagerTopicService.editTopic(topicId, topicBO);
        return Result.ok();
    }

    /**
     * 删除主题赛
     * @param topicId 主题赛Id
     */
    @DeleteMapping(value = "/{topicId}")
    public Result delTopic(@PathVariable Integer topicId){
        yoyoManagerTopicService.delTopic(topicId);
        return Result.ok();
    }

    @PostMapping(value = "/{topicId}/")
    public Result releaseTopic(@PathVariable Integer topicId)
    {
        yoyoManagerTopicService.releaseTopic(topicId);
        return Result.ok();
    }

}
