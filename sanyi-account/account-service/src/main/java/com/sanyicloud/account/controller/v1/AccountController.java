package com.sanyicloud.account.controller.v1;

import com.sanyocloud.account.entity.bo.AccountBO;
import com.sanyicloud.sanyi.common.core.util.Result;
import com.sanyocloud.account.feign.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * by zhaowenyuan create 2021/11/4 12:12
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1")
public class AccountController {

    @GetMapping(value = "/token")
    public Result<String> getAccountByToken(String token)
    {
        log.info("token->{}",token);
        return null;
    }

    /**
     * 设备号登录
     * @author ant
     * @date 2021/11/4 12:16
     * @param accountBO 账号所需设备号
     * @return Result
     */
    @PostMapping(value = "/topic/login")
    public Result login(@RequestBody @Valid AccountBO accountBO)
    {
        log.info("ssss->{}",accountBO);
        return null;
    }

}
