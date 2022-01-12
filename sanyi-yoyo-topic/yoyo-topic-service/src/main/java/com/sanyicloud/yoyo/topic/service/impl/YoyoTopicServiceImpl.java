package com.sanyicloud.yoyo.topic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sanyicloud.yoyo.topic.entity.po.TYoyoTopic;
import com.sanyicloud.yoyo.topic.mapper.YoyoTopicMapper;
import com.sanyicloud.yoyo.topic.service.YoyoTopicService;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * by zhaowenyuan create 2022/1/6 12:02
 */
@Service
public class YoyoTopicServiceImpl extends ServiceImpl<YoyoTopicMapper, TYoyoTopic> implements YoyoTopicService {

    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("GMT"));
        System.out.println(now);
    }
}
