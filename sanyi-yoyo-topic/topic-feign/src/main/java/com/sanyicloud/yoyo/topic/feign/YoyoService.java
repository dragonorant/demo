package com.sanyicloud.yoyo.topic.feign;

import com.sanyicloud.sanyi.common.core.constant.ServiceNameConstants;
import com.sanyicloud.sanyi.common.core.util.Result;
import com.sanyicloud.yoyo.topic.feign.fallback.YoyoServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * by zhaowenyuan create 2021/10/22 00:02
 */
@FeignClient(contextId = "yoyoService",value = ServiceNameConstants.YOYO_SERVICE,
    fallbackFactory = YoyoServiceFallbackFactory.class,
    path = "/yoyo/game")
public interface YoyoService {

    Result demo();

}
