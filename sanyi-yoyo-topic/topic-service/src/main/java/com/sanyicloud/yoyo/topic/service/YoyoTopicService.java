package com.sanyicloud.yoyo.topic.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sanyicloud.sanyi.common.core.exception.ResultException;
import com.sanyicloud.sanyi.common.core.util.EnumUtils;
import com.sanyicloud.sanyi.common.core.util.Result;
import com.sanyicloud.sanyi.common.mybatis.utils.PageUtils;
import com.sanyicloud.yoyo.topic.entity.enums.EnableStatusEnum;
import com.sanyicloud.yoyo.topic.entity.po.YoyoTopic;

/**
 * by zhaowenyuan create 2022/1/6 12:01
 */
public interface YoyoTopicService extends IService<YoyoTopic> {
    LambdaQueryWrapper<YoyoTopic> queryMapper = new LambdaQueryWrapper<YoyoTopic>()
            .eq(YoyoTopic::getReleaseState, Boolean.TRUE);

    default Result home(){
        YoyoTopic yoyoTopic = this.getOne(queryMapper
                .eq(YoyoTopic::getEnableStatus, EnableStatusEnum.ONGOING)
        );
        return Result.responseData()
                .addParam("currentTopic", yoyoTopic)
                .build();
    }

    default Result listTopic(Short code, Integer limit, Integer pageSize){
        EnableStatusEnum enableStatus = EnumUtils.getByCode(code, EnableStatusEnum.class);
        if (null == enableStatus || EnableStatusEnum.ONGOING.equals(enableStatus)){
            throw new ResultException("状态码不正确");
        }
        IPage<YoyoTopic> page =
                this.page(new Page<>(limit, pageSize),
                        queryMapper.eq(YoyoTopic::getEnableStatus, enableStatus)
                                .orderBy( true,
                                        EnableStatusEnum.FUTURE.equals(enableStatus),
                                        YoyoTopic::getStartTime)
                );
        return Result.ok(new PageUtils(page));
    }
}
