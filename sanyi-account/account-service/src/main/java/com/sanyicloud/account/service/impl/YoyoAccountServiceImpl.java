package com.sanyicloud.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sanyicloud.account.entity.bo.AccountBO;
import com.sanyicloud.account.entity.po.YoyoAccount;
import com.sanyicloud.account.entity.po.YoyoThird;
import com.sanyicloud.account.entity.vo.AccountVO;
import com.sanyicloud.account.mapper.YoyoAccountMapper;
import com.sanyicloud.account.service.YoyoAccountService;
import com.sanyicloud.account.service.YoyoThirdService;
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
public class YoyoAccountServiceImpl extends ServiceImpl<YoyoAccountMapper, YoyoAccount> implements YoyoAccountService {

    @Autowired
    YoyoThirdService yoyoThirdService;
    @Autowired
    RedissonClient redissonClient;

    @Override
    public Result login(AccountBO accountBO)
    {
        String deviceNum = accountBO.getDeviceNum();
        Short deviceType = accountBO.getDeviceType();
        // 以其作为 accountId
        String accountId = IdUtils.strConvertNum(deviceNum);
        YoyoAccount yoyoAccount = this.baseMapper.selectOne(
                new LambdaQueryWrapper<YoyoAccount>()
                        .eq(YoyoAccount::getAccountId, accountId)
        );
        // 如果不为空 -- 则说明为 登录
        if (ObjectUtils.isNotEmpty(yoyoAccount)){
            YoyoThird yoyoThird = yoyoThirdService.getOne(
                    new LambdaQueryWrapper<YoyoThird>()
                            .eq(YoyoThird::getAccountId, accountId)
                            .eq(YoyoThird::getDeviceNum, deviceNum)
                            .eq(YoyoThird::getDeviceType, accountBO.getDeviceType())
            );
            // 则说明此账号存在
            if (ObjectUtils.isNotEmpty(yoyoThird)){
                return Result.ok(AccountVO.covert(yoyoAccount,yoyoThird));
            }
        }
        yoyoAccount = YoyoAccount.builder()
                .accountId(accountId)
                .avatar(accountBO.getAvatar())
                .nickname(accountBO.getNickname())
                .build();
        YoyoThird yoyoThird = YoyoThird.builder()
                .accountId(accountId)
                .deviceNum(deviceNum)
                .deviceType(deviceType)
                .build();

        createAccount(yoyoThird, yoyoAccount);

        return Result.ok(AccountVO.covert(yoyoAccount,yoyoThird));
    }

    @Transactional
    public void createAccount(YoyoThird yoyoThird, YoyoAccount yoyoAccount)
    {
        yoyoThirdService.save(yoyoThird);
        this.baseMapper.insert(yoyoAccount);
    }

}
