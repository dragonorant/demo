package com.sanyicloud.yoyo.topic.entity.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
    * yoyo 主题赛参赛 作品
    */
@Data
public class YoyoTopicEntry implements Serializable {
    /**
    * 自增主键
    */
    private Integer id;

    /**
    * 参赛人Id
    */
    private String pothunterId;

    /**
    * 参赛作品详情
    */
    private String entriesDetail;

    /**
    * 参赛作品当前得票数
    */
    private Long entriesVoteNum;

    /**
    * 参赛时间
    */
    private Date createTime;

    /**
    * 修改时间
    */
    private Date updateTime;

    /**
    * 主题赛主题Id
    */
    private Integer topicId;

    private static final long serialVersionUID = 1L;
}