package com.sanyicloud.account.entity.vo;

import cn.hutool.json.JSONUtil;
import com.sanyicloud.account.entity.bo.AccountBO;
import com.sanyicloud.account.entity.po.SanyiAccount;
import com.sanyicloud.account.entity.po.SanyiThird;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

/**
 * by zhaowenyuan create 2021/12/30 10:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AccountVO extends AccountBO {
    /**
     * 账号id
     */
    private String accountId;
    /**
     * 推广码
     */
    private String promoCode;

    public static String covert(SanyiAccount sanyiAccount, SanyiThird sanyiThird){
        AccountVO accountVO = new AccountVO();
        accountVO.setAccountId(sanyiAccount.getAccountId());
        String avatar = sanyiAccount.getAvatar();

        if (StringUtils.isNotEmpty(avatar))
            accountVO.setAvatar(avatar);
        String nickname = sanyiAccount.getNickname();
        if (StringUtils.isNotEmpty(nickname))
            accountVO.setNickname(nickname);

        accountVO.setDeviceNum(sanyiThird.getDeviceNum());
        accountVO.setDeviceType(sanyiThird.getDeviceType());
        accountVO.setPromoCode(sanyiThird.getPromoCode());
        return JSONUtil.toJsonStr(accountVO);
    }

}
