package com.sanyocloud.account.feign.fallback;

import com.sanyicloud.sanyi.common.core.util.Result;
import com.sanyocloud.account.entity.bo.AccountBO;
import com.sanyocloud.account.feign.AccountService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * by zhaowenyuan create 2021/11/3 15:16
 */
@Slf4j
@Component
public class AccountFallbackFactory implements FallbackFactory<AccountService> {
    @Override
    public AccountService create(Throwable cause) {
        AccountServiceFallbackImpl accountServiceFallback = new AccountServiceFallbackImpl();
        accountServiceFallback.setCause(cause);
        return accountServiceFallback;
    }
}
@Component
class AccountServiceFallbackImpl implements AccountService{

    @Setter
    private Throwable cause;

    @Override
    public Result<String> getAccountByToken(String token) {
        return Result.failed("请稍后再试");
    }

    @Override
    public Result<String> login(AccountBO accountBO) {
        return Result.failed("请稍后再试");
    }
}
