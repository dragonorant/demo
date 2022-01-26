package com.sanyicloud.account.controller.v1;

import com.sanyicloud.account.entity.bo.AccountBO;
import com.sanyicloud.account.entity.bo.DeviceBO;
import com.sanyicloud.account.entity.bo.InviteBO;
import com.sanyicloud.account.service.SanyiAccountService;
import com.sanyicloud.sanyi.common.core.util.RequestUtils;
import com.sanyicloud.sanyi.common.core.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

    /**
     * 根据设备码 获取 本机设备 推广码
     * 获取推广码
     */
    @GetMapping(value = "/promo/code")
    public Result getPromoCode(@RequestBody @Valid DeviceBO deviceBO)
    {
        log.info("get promo code");
        AccountBO accountBO = new AccountBO();
        accountBO.setDeviceNum(deviceBO.getDeviceNum());
        accountBO.setDeviceType(deviceBO.getDeviceType());
        return sanyiAccountService.login(accountBO);
    }

    /**
     * 填写 邀请码 推广
     *
     * todo  邀请人 获取 一份礼物
     *      被邀请人 获取 另一份礼物
     *      需要有一个推广通知表 -- 记录推广人的被邀请信息
     *
     *      需要有一个推广记录表 -- 记录邀请人的推广人
     */
    @PostMapping(value = "/invite")
    public Result inviteAccount(@RequestBody @Valid InviteBO inviteBO)
    {
        sanyiAccountService.inviteAccount(inviteBO);
        return Result.ok();
    }


    @RequestMapping(value = "/demo")
    public void demo(HttpServletRequest request, @RequestParam String accountId, MultipartFile file){
        Enumeration<String> parameterNames = request.getParameterNames();
        String s = RequestUtils.ReadAsChars(request);
        System.out.println(s);
    }
}
