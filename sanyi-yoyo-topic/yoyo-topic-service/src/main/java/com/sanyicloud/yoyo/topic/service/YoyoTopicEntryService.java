package com.sanyicloud.yoyo.topic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sanyicloud.sanyi.common.core.util.Result;
import com.sanyicloud.yoyo.topic.entity.bo.UploadTopicBO;
import com.sanyicloud.yoyo.topic.entity.po.TYoyoTopicEntry;

/**
 * by zhaowenyuan create 2022/1/14 15:20
 */
public interface YoyoTopicEntryService extends IService<TYoyoTopicEntry> {
    /**
     * 需要创建的表名
     */
    String TABLE_NAME = "t_yoyo_topic_entry";

    void createTopicEntry(Integer topicId);

    Result topicUpload(Integer topicId, UploadTopicBO uploadTopicBO);
}
