package com.sanyicloud.yoyo.topic.entity.bo;

import lombok.Data;

/**
 * by zhaowenyuan create 2022/1/6 12:03
 * 新建或者编辑 topic
 */
@Data
public class TopicBO {
    /**
     * 主题名称
     */
    private String topicName;
    /**
     * 主题封面 -- 可能是 app 打包 又或者 oss
     */
    private String topicCover;
    /**
     * 主题规则
     */
    private String topicRule;
    /**
     * 主题开始时间
     * 传 yyyy-MM-dd 或者 yyyy-MM-dd HH:mm:ss
     * 具体的 还需要 策划进行 确定 -- 如果 为 yyyy-MM-dd 则需要 添加 至 atStartOfDay
     */
    private String startTime;
    /**
     * 主题结束时间
     *  传 yyyy-MM-dd 或者 yyyy-MM-dd HH:mm:ss
     *  具体的 还需要 策划进行 确定 -- 如果 为 yyyy-MM-dd 则需要 添加 至 atTime(23,59,59)
     */
    private String endTime;
    /**
     * 上传开始时间
     * 传 yyyy-MM-dd 或者 yyyy-MM-dd HH:mm:ss
     * 具体的 还需要 策划进行 确定 -- 如果 为 yyyy-MM-dd 则需要 添加 至 atStartOfDay
     */
    private String uploadStartTime;
    /**
     * 上传结束时间
     *  传 yyyy-MM-dd 或者 yyyy-MM-dd HH:mm:ss
     *  具体的 还需要 策划进行 确定 -- 如果 为 yyyy-MM-dd 则需要 添加 至 atTime(23,59,59)
      */
    private String uploadEndTime;
    /**
     * 评比开始时间
     * 传 yyyy-MM-dd 或者 yyyy-MM-dd HH:mm:ss
     * 具体的 还需要 策划进行 确定 -- 如果 为 yyyy-MM-dd 则需要 添加 至 atStartOfDay
     */
    private String compareStartTime;
    /**
     * 评比结束时间
     *  传 yyyy-MM-dd 或者 yyyy-MM-dd HH:mm:ss
     *  具体的 还需要 策划进行 确定 -- 如果 为 yyyy-MM-dd 则需要 添加 至 atTime(23,59,59)
     */
    private String compareEndTime;


}
