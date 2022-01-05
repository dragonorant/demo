package com.sanyicloud.account.entity.vo;

import com.sanyicloud.account.entity.bo.AccountBO;
import com.sanyicloud.account.entity.po.SanyiAccount;
import com.sanyicloud.account.entity.po.SanyiThird;
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

    public static AccountVO covert(SanyiAccount sanyiAccount, SanyiThird sanyiThird){
        AccountVO accountVO = new AccountVO();
        accountVO.setAccountId(sanyiAccount.getAccountId());
        accountVO.setAvatar(sanyiAccount.getAvatar());
        accountVO.setNickname(sanyiAccount.getNickname());
        accountVO.setDeviceNum(sanyiThird.getDeviceNum());
        accountVO.setDeviceType(sanyiThird.getDeviceType());
        return accountVO;
    }

}
