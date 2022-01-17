package com.sanyicloud.yoyo.topic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sanyicloud.sanyi.common.core.constant.CommonConstants;
import com.sanyicloud.sanyi.common.core.util.Result;
import com.sanyicloud.sanyi.common.mybatis.mapper.DynamicTableNameMapper;
import com.sanyicloud.yoyo.topic.entity.bo.UploadTopicBO;
import com.sanyicloud.yoyo.topic.entity.po.TYoyoTopicEntry;
import com.sanyicloud.yoyo.topic.mapper.YoyoTopicEntryMapper;
import com.sanyicloud.yoyo.topic.service.YoyoTopicEntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * by zhaowenyuan create 2022/1/14 15:21
 */
@Slf4j
@Service
public class YoyoTopicEntryServiceImpl extends ServiceImpl<YoyoTopicEntryMapper,TYoyoTopicEntry> implements YoyoTopicEntryService {

    @Autowired
    DynamicTableNameMapper dynamicTableNameMapper;

    @Override
    public synchronized void createTopicEntry(Integer topicId) {
        String tableName = TABLE_NAME + CommonConstants.TABLE_SYMBOL + topicId;
        int i = dynamicTableNameMapper.tableIsExist(tableName);
        if (i > 0){
            dynamicTableNameMapper.dropTable(tableName);
        }
        dynamicTableNameMapper.createTable(tableName,TABLE_NAME);
    }

    @Override
    public Result topicUpload(Integer topicId, UploadTopicBO uploadTopicBO)
    {
        return null;
    }
}
