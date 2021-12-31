package com.sanyicloud.account.feign;

import com.sanyicloud.sanyi.common.core.constant.ServiceNameConstants;
import com.sanyicloud.sanyi.common.core.util.Result;
import com.sanyicloud.account.entity.bo.AccountBO;
import com.sanyicloud.account.feign.fallback.AccountFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * by zhaowenyuan create 2021/11/3 15:16
 */
@FeignClient(contextId = "accountService",value = ServiceNameConstants.ACCOUNT_SERVICE,
        fallbackFactory = AccountFallbackFactory.class,
        path = "/yoyo/account")
public interface AccountService {
    /**
     * 通过token 获取账号信息
     * @param token
     * @return
     */
    Result getAccountByToken(String token);

    /**
     * 等同于账号登录
     * @param accountBO
     * @return
     */
    Result login(AccountBO accountBO);

}
