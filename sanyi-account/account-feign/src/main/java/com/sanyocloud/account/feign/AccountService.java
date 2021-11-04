package com.sanyocloud.account.feign;

import com.sanyicloud.sanyi.common.core.constant.ServiceNameConstants;
import com.sanyicloud.sanyi.common.core.util.Result;
import com.sanyocloud.account.entity.bo.AccountBO;
import com.sanyocloud.account.feign.fallback.AccountFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * by zhaowenyuan create 2021/11/3 15:16
 */
@FeignClient(contextId = "accountService",value = ServiceNameConstants.ACCOUNT_SERVICE,
        fallbackFactory = AccountFallbackFactory.class,
        path = "/yoyo/account")
@RequestMapping(value = "/v1")
public interface AccountService {
    /**
     * 通过token 获取账号信息
     * @param token
     * @return
     */
    @GetMapping(value = "/token")
    Result<String> getAccountByToken(String token);

    /**
     * 等同于账号登录
     * @param accountBO
     * @return
     */
    @PostMapping(value = "/topic/login")
    Result<String> login(AccountBO accountBO);

}
