package com.sanyicloud.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sanyicloud.account.entity.bo.AccountBO;
import com.sanyicloud.account.entity.po.SanyiAccount;
import com.sanyicloud.account.entity.po.SanyiThird;
import com.sanyicloud.account.entity.vo.AccountVO;
import com.sanyicloud.account.mapper.SanyiAccountMapper;
import com.sanyicloud.account.service.SanyiAccountService;
import com.sanyicloud.account.service.SanyiThirdService;
import com.sanyicloud.sanyi.common.core.util.IdUtils;
import com.sanyicloud.sanyi.common.core.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * by zhaowenyuan create 2021/12/29 14:32
 */
@Slf4j
@Service
public class SanyiAccountServiceImpl extends ServiceImpl<SanyiAccountMapper, SanyiAccount> implements SanyiAccountService {

    @Autowired
    SanyiThirdService sanyiThirdService;
    @Autowired
    RedissonClient redissonClient;

    @Override
    public Result login(AccountBO accountBO)
    {
        String deviceNum = accountBO.getDeviceNum();
        Short deviceType = accountBO.getDeviceType();
        // 以其作为 accountId
        String accountId = IdUtils.strConvertNum(deviceNum);
        SanyiAccount sanyiAccount = this.baseMapper.selectOne(
                new LambdaQueryWrapper<SanyiAccount>()
                        .eq(SanyiAccount::getAccountId, accountId)
        );
        // 如果不为空 -- 则说明为 登录
        if (ObjectUtils.isNotEmpty(sanyiAccount)){
            SanyiThird sanyiThird = sanyiThirdService.getOne(
                    new LambdaQueryWrapper<SanyiThird>()
                            .eq(SanyiThird::getAccountId, accountId)
                            .eq(SanyiThird::getDeviceNum, deviceNum)
                            .eq(SanyiThird::getDeviceType, accountBO.getDeviceType())
            );
            // 则说明此账号存在
            if (ObjectUtils.isNotEmpty(sanyiThird)){
                return Result.ok(AccountVO.covert(sanyiAccount,sanyiThird));
            }
        }
        sanyiAccount = SanyiAccount.builder()
                .accountId(accountId)
                .avatar(accountBO.getAvatar())
                .nickname(accountBO.getNickname())
                .build();
        SanyiThird sanyiThird = SanyiThird.builder()
                .accountId(accountId)
                .deviceNum(deviceNum)
                .deviceType(deviceType)
                .build();

        createAccount(sanyiThird, sanyiAccount);

        return Result.ok(AccountVO.covert(sanyiAccount,sanyiThird));
    }

    @Transactional
    public void createAccount(SanyiThird sanyiThird, SanyiAccount sanyiAccount)
    {
        sanyiThirdService.save(sanyiThird);
        this.baseMapper.insert(sanyiAccount);
    }

}
