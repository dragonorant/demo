package com.sanyicloud.yoyo.feign;

import com.sanyicloud.sanyi.common.core.constant.ServiceNameConstants;
import com.sanyicloud.sanyi.common.core.util.Result;
import com.sanyicloud.yoyo.feign.fallback.DemoServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * by zhaowenyuan create 2021/10/22 00:02
 */
@FeignClient(contextId = "demoService",value = ServiceNameConstants.UMPS_SERVICE,
    fallbackFactory = DemoServiceFallbackFactory.class)
public interface DemoService {

    Result<String> demo();

}
