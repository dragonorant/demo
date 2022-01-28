package com.sanyicloud.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sanyicloud.account.entity.bo.AccountBO;
import com.sanyicloud.account.entity.bo.InviteBO;
import com.sanyicloud.account.entity.po.SanyiAccount;
import com.sanyicloud.sanyi.common.core.util.Result;

/**
 * by zhaowenyuan create 2021/12/29 14:32
 */
public interface SanyiAccountService extends IService<SanyiAccount> {

    Result login(AccountBO accountBO);

    void inviteAccount(InviteBO inviteBO);
}
