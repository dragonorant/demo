package com.sanyicloud.account.controller.v1;

import com.sanyicloud.account.entity.bo.AccountBO;
import com.sanyicloud.account.service.SanyiAccountService;
import com.sanyicloud.sanyi.common.core.util.IdUtils;
import com.sanyicloud.sanyi.common.core.util.RequestUtils;
import com.sanyicloud.sanyi.common.core.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

/**
 * by zhaowenyuan create 2021/11/4 12:12
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1")
public class AccountController {
    @Autowired
    SanyiAccountService sanyiAccountService;

    /**
     * 设备号登录 ｜ 注册 存在即登录, 不存在即注册
     * @author ant
     * @date 2021/11/4 12:16
     * @param accountBO 账号所需设备号
     * @return Result
     */
    @PostMapping(value = "/login")
    public Result login(@RequestBody @Valid AccountBO accountBO)
    {
        return sanyiAccountService.login(accountBO);
    }

    @RequestMapping(value = "/demo")
    public void demo(HttpServletRequest request, @RequestParam String accountId, MultipartFile file){
        Enumeration<String> parameterNames = request.getParameterNames();
        String s = RequestUtils.ReadAsChars(request);
        System.out.println(s);
    }
    public static void main(String[] args) {
        String s = IdUtils.strConvertNum("s");
        System.out.println(s);
        System.out.println(s.length());
    }

}
