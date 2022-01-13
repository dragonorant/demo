package com.sanyicloud.yoyo.topic.controller;

import com.sanyicloud.sanyi.common.core.util.Result;
import com.sanyicloud.sanyi.common.dynamic.annotation.Read;
import com.sanyicloud.sanyi.common.mybatis.mapper.DynamicTableNameMapper;
import com.sanyicloud.yoyo.topic.entity.bo.UploadTopicBO;
import com.sanyicloud.yoyo.topic.service.YoyoTopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * by zhaowenyuan create 2021/11/3 15:59
 */
@Slf4j
@RequestMapping(value = "/v1")
@RestController
public class TopicController {
    @Autowired
    YoyoTopicService yoyoTopicService;
    @Autowired
    DynamicTableNameMapper dynamicTableNameMapper;

    @GetMapping(value = "/test")
    @Read
    public String test(String topicId){
        int i = dynamicTableNameMapper.tableIsExist(topicId);
        System.out.println(i);
        return topicId;
    }

    /**
     * 获取主题赛首页列表
     */
    @GetMapping(value = "/home")
    public Result home()
    {
        return yoyoTopicService.home();
    }

    /**
     * 获取主题赛列表
     */
    @GetMapping(value = "/list")
    public Result listTopic(@RequestParam(value = "code", required = false) Short code,
                            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "3") Integer pageSize)
    {
        return yoyoTopicService.listTopic(code, page, pageSize);
    }

    @PostMapping(value = "/upload")
    public Result uploadTopic(@RequestBody UploadTopicBO uploadTopicBO)
    {
        return Result.ok();
    }


    /**
     * 根据 主题Id 获取主题赛的奖励信息
     * @param topicId 主题id
     */
    @GetMapping(value = "/reward/{topicId}")
    public Result rewardTopic(@PathVariable Integer topicId)
    {
        return yoyoTopicService.rewardTopic(topicId);
    }


}
