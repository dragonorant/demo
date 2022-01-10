package com.sanyicloud.yoyo.topic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sanyicloud.sanyi.common.core.util.Result;
import com.sanyicloud.yoyo.topic.entity.po.YoyoTopic;
import com.sanyicloud.yoyo.topic.mapper.YoyoTopicMapper;
import com.sanyicloud.yoyo.topic.service.YoyoTopicService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * by zhaowenyuan create 2022/1/6 12:02
 */
@Service
public class YoyoTopicServiceImpl extends ServiceImpl<YoyoTopicMapper, YoyoTopic> implements YoyoTopicService {

    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("GMT"));
        System.out.println(now);
    }
}
