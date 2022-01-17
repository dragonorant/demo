package com.sanyicloud;

import com.sanyicloud.sanyi.common.dynamic.annotation.Read;
import com.sanyicloud.sanyi.common.mybatis.mapper.DynamicTableNameMapper;
import com.sanyicloud.yoyo.topic.entity.bo.TopicBO;
import com.sanyicloud.yoyo.topic.service.YoyoManagerTopicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * by zhaowenyuan create 2022/1/13 10:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest {

    @Autowired
    DynamicTableNameMapper dynamicTableNameMapper;
    @Autowired
    YoyoManagerTopicService yoyoManagerTopicService;

    @Test
    @Read
    public void test(){
        TopicBO topicBO = new TopicBO();
        topicBO.setTopicRule("topic rule");
        topicBO.setTopicName("topic name");
        topicBO.setTopicCover("topic Cover");
        topicBO.setCompareStartTime("2022-01-14 15:44:44");
        topicBO.setCompareEndTime("2022-01-14 15:44:44");
        topicBO.setEndTime("2022-01-14 15:44:44");
        topicBO.setStartTime("2022-01-14 15:44:44");
        topicBO.setUploadEndTime("2022-01-14 15:44:44");
        topicBO.setUploadStartTime("2022-01-14 15:44:44");
        yoyoManagerTopicService.editTopic(14, topicBO);
    }

}
