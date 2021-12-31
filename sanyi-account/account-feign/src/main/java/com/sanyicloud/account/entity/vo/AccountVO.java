package com.sanyicloud.account.entity.vo;

import com.sanyicloud.account.entity.bo.AccountBO;
import com.sanyicloud.account.entity.po.YoyoAccount;
import com.sanyicloud.account.entity.po.YoyoThird;
import lombok.Data;

/**
 * by zhaowenyuan create 2021/12/30 10:14
 */
@Data
public class AccountVO extends AccountBO {
    /**
     * 账号id
     */
    private String accountId;

    public static AccountVO covert(YoyoAccount yoyoAccount, YoyoThird yoyoThird){
        AccountVO accountVO = new AccountVO();
        accountVO.setAccountId(yoyoAccount.getAccountId());
        accountVO.setAvatar(yoyoAccount.getAvatar());
        accountVO.setNickname(yoyoAccount.getNickname());
        accountVO.setDeviceNum(yoyoThird.getDeviceNum());
        accountVO.setDeviceType(yoyoThird.getDeviceType());
        return accountVO;
    }

}
