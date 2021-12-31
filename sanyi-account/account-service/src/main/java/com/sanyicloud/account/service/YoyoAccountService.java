package com.sanyicloud.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sanyicloud.account.entity.bo.AccountBO;
import com.sanyicloud.account.entity.po.YoyoAccount;
import com.sanyicloud.sanyi.common.core.util.Result;

/**
 * by zhaowenyuan create 2021/12/29 14:32
 */
public interface YoyoAccountService extends IService<YoyoAccount> {
    Result login(AccountBO accountBO);
}
