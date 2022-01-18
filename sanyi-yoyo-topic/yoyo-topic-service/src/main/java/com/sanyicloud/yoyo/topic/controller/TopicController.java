package com.sanyicloud.yoyo.topic.controller;

import com.sanyicloud.sanyi.common.core.util.Result;
import com.sanyicloud.sanyi.common.mybatis.mapper.DynamicTableNameMapper;
import com.sanyicloud.yoyo.topic.entity.bo.UploadTopicBO;
import com.sanyicloud.yoyo.topic.service.YoyoTopicEntryService;
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
    YoyoTopicEntryService yoyoTopicEntryService;
    @Autowired
    DynamicTableNameMapper dynamicTableNameMapper;

    @GetMapping(value = "/test")
    public String test(String topicId){
        log1();
        log.info("begin topic {}", topicId);
        log2();
        return topicId;
    }

    private void log1(){
        log.info("log 1");
    }
    private void log2(){
        log.info("log 2");
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

    /**
     * 主题赛 上传作品
     * @param topicId 主题赛Id
     * @param uploadTopicBO 参赛信息 -- 参赛人、参赛作品
     * @return
     */
    @PostMapping(value = "/upload/{topicId}")
    public Result topicUpload(@PathVariable Integer topicId,
                              @RequestBody UploadTopicBO uploadTopicBO)
    {
        return yoyoTopicEntryService.topicUpload(topicId,uploadTopicBO);
    }

}
