package com.sanyicloud.yoyo.feign.fallback;

import com.sanyicloud.sanyi.common.core.util.Result;
import com.sanyicloud.yoyo.feign.YoyoService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * by zhaowenyuan create 2021/10/22 00:05
 */
@Slf4j
@Component
public class YoyoServiceFallbackFactory implements FallbackFactory<YoyoService>{

    @Override
    public YoyoService create(Throwable cause) {
        YoyoServiceFallbackImpl demoServiceFallback = new YoyoServiceFallbackImpl();
        demoServiceFallback.setCause(cause);
        return demoServiceFallback;
    }
}
@Component
class YoyoServiceFallbackImpl implements YoyoService {

    @Setter
    private Throwable cause;

    @Override
    public Result<String> demo() {
        return null;
    }
}
