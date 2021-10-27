package com.sanyicloud.yoyo.service;

import com.sanyicloud.sanyi.common.core.util.Result;
import com.sanyicloud.yoyo.entity.Demo;
import com.sanyicloud.yoyo.mapper.DemoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * by zhaowenyuan create 2021/10/22 14:57
 */
@Slf4j
@Service
public class TestService {

    @Resource
    DemoMapper demoMapper;

    public Result<String> test(String params) {
        log.info(params);

        demoMapper.insert(Demo.builder()
                        .age(20)
                        .name("name")
                .build());

        return Result.failed();
    }

}
