package com.sanyicloud.yoyo.topic.entity.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * by zhaowenyuan create 2022/1/11 17:37
 * 主题赛上传 所需参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UploadTopicBO extends TopicCommonBO {
    /**
     * 上传的作品信息
     */
    private String works;

}
