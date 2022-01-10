package com.sanyicloud.yoyo.topic.controller;

import com.sanyicloud.sanyi.common.core.util.Result;
import com.sanyicloud.yoyo.topic.entity.bo.TopicBO;
import com.sanyicloud.yoyo.topic.entity.po.YoyoTopicEntry;
import com.sanyicloud.yoyo.topic.mapper.YoyoTopicEntryMapper;
import com.sanyicloud.yoyo.topic.service.YoyoTopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * by zhaowenyuan create 2021/11/3 15:59
 */
@Slf4j
@RequestMapping(value = "/v1")
@RestController
public class TopicController {

    @Autowired
    YoyoTopicEntryMapper yoyoTopicEntryMapper;

    @Autowired
    YoyoTopicService yoyoTopicService;

    @RequestMapping(value = "/test")
    public String test(Integer topicId){
        log.info("测试能否发送 kafka");
        throw new RuntimeException("测试抛出一次");
    }

    @PostMapping(value = "/{topicId}")
    public Result editTopic(@PathVariable Integer topicId,
                            @Valid TopicBO topicBO)
    {
        yoyoTopicService.editTopic(topicId, topicBO);
        return Result.ok();
    }


}
