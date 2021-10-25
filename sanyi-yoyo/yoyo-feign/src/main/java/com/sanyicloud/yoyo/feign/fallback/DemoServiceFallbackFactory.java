package com.sanyicloud.yoyo.feign.fallback;

import com.sanyicloud.sanyi.common.core.util.Result;
import com.sanyicloud.yoyo.feign.DemoService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * by zhaowenyuan create 2021/10/22 00:05
 */
@Slf4j
@Component
public class DemoServiceFallbackFactory implements FallbackFactory<DemoService>{

    @Override
    public DemoService create(Throwable cause) {
        DemoServiceFallbackImpl demoServiceFallback = new DemoServiceFallbackImpl();
        demoServiceFallback.setCause(cause);
        return demoServiceFallback;
    }
}
@Component
class DemoServiceFallbackImpl implements DemoService {

    @Setter
    private Throwable cause;

    @Override
    public Result<String> demo() {
        return null;
    }
}
