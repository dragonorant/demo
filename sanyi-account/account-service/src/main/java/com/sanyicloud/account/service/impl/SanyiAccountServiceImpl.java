package com.sanyicloud.account.service.impl;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sanyicloud.account.entity.bo.AccountBO;
import com.sanyicloud.account.entity.bo.InviteBO;
import com.sanyicloud.account.entity.enums.AccountSourceEnum;
import com.sanyicloud.account.entity.enums.DeviceTypeEnum;
import com.sanyicloud.account.entity.enums.MessageTypeEnum;
import com.sanyicloud.account.entity.po.*;
import com.sanyicloud.account.entity.vo.AccountVO;
import com.sanyicloud.account.mapper.SanyiAccountMapper;
import com.sanyicloud.account.service.*;
import com.sanyicloud.sanyi.common.core.exception.ResultException;
import com.sanyicloud.sanyi.common.core.util.EnumUtils;
import com.sanyicloud.sanyi.common.core.util.IdUtils;
import com.sanyicloud.sanyi.common.core.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * by zhaowenyuan create 2021/12/29 14:32
 */
@Slf4j
@Service
public class SanyiAccountServiceImpl extends ServiceImpl<SanyiAccountMapper, SanyiAccount> implements SanyiAccountService {

    @Autowired
    SanyiThirdService sanyiThirdService;
    @Autowired
    SanyiAccountPromoService sanyiPromoService;
    @Autowired
    SanyiAccountInviteService accountInviteService;
    @Autowired
    SanyiAccountMessageService messageService;
    @Autowired
    RedissonClient redissonClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result login(AccountBO accountBO)
    {
        String deviceNum = accountBO.getDeviceNum();
        Short deviceType = accountBO.getDeviceType();
        DeviceTypeEnum deviceTypeEnum = EnumUtils.getByCode(deviceType, DeviceTypeEnum.class);
        if (null == deviceTypeEnum){
            deviceTypeEnum = DeviceTypeEnum.OTHER;
        }
        String guid = deviceNum + ":" + deviceTypeEnum.getCode();
        // 以其作为 accountId
        String accountId = IdUtils.strConvertNum(guid);
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
                .build();
        // short 数组
        String[] shortNum = IdUtils.shortNum(guid);

        assert shortNum.length > 1;

        String promoCode = shortNum[0];

        SanyiAccountPromo accountPromo = SanyiAccountPromo.builder()
                .accountId(accountId)
                .promoCode(promoCode)
                .build();

        SanyiThird sanyiThird = SanyiThird.builder()
                .accountId(accountId)
                .deviceNum(deviceNum)
                .deviceType(deviceTypeEnum.getCode())
                .promoCode(promoCode)
                .accountSource(AccountSourceEnum.COME_FROM.getCode())
                .build();

        createAccount(sanyiThird, sanyiAccount, accountPromo);

        return Result.ok(AccountVO.covert(sanyiAccount, sanyiThird));
    }

    /**
     * 添加邀请码
     * @param inviteBO 邀请码 包含信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void inviteAccount(InviteBO inviteBO)
    {
        String accountId = inviteBO.getAccountId();
        // 邀请码
        String promoCode = inviteBO.getPromoCode();
        String deviceNum = inviteBO.getDeviceNum();
        Short deviceType = inviteBO.getDeviceType();
        DeviceTypeEnum deviceTypeEnum = EnumUtils.getByCode(deviceType, DeviceTypeEnum.class);
        if (null == deviceTypeEnum){
            deviceTypeEnum = DeviceTypeEnum.OTHER;
        }
        String guid = deviceNum + ":" + deviceTypeEnum.getCode();
        String deviceAccountId = IdUtils.strConvertNum(guid);
        // 不为空
        if (StringUtils.isNotEmpty(accountId)){
            //如果 不为空, 则需要验证是否为 设备信息 加密后的 accountId, 如不是, 则 直接拒绝此次请求。因为 其属于 无效请求 403
            if (!accountId.equals(deviceAccountId)){
                throw new ResultException("data validation failed", 403);
            }
        }
        accountId = deviceAccountId;
        // 根据 邀请人 获取 数据信息
        SanyiAccount sanyiAccount = this.baseMapper.selectOne(
                new LambdaQueryWrapper<SanyiAccount>()
                        .eq(SanyiAccount::getAccountId, accountId)
        );

        // 如果为空 -- 则说明 此账号未进行注册 则进行注册
        if (ObjectUtils.isEmpty(sanyiAccount))
        {
            sanyiAccount = SanyiAccount.builder()
                    .accountId(accountId)
                    .inviteState(Boolean.FALSE)
                    .build();
            // short 数组
            String[] shortNum = IdUtils.shortNum(guid);

            assert shortNum.length > 1;
            String inviterPromoCode = shortNum[0];

            SanyiAccountPromo accountShort = SanyiAccountPromo.builder()
                    .accountId(accountId)
                    .promoCode(inviterPromoCode)
                    .build();

            SanyiThird sanyiThird = SanyiThird.builder()
                    .accountId(accountId)
                    .deviceNum(deviceNum)
                    .deviceType(deviceTypeEnum.getCode())
                    .promoCode(inviterPromoCode)
                    .accountSource(AccountSourceEnum.INVITE.getCode())
                    .build();

            createAccount(sanyiThird, sanyiAccount, accountShort);
        }
        // 如注册, 进行邀请信息的绑定,   200
        // 判断邀请码的 有效性
        SanyiAccountPromo accountPromo = sanyiPromoService.getOne(
                new LambdaQueryWrapper<SanyiAccountPromo>()
                        .eq(SanyiAccountPromo::getPromoCode, promoCode)
        );
        // 邀请码未 查询到对应的账号 Id  则说明 邀请码无效
        if (ObjectUtils.isEmpty(accountPromo)){
            throw new ResultException("The invitation code is invalid. Please fill in a valid invitation code");
        }
        Boolean inviteState = sanyiAccount.getInviteState();
        // todo 查询是否使用过此 邀请
        long count = accountInviteService.count(
                new LambdaQueryWrapper<SanyiAccountInvite>()
                        .eq(SanyiAccountInvite::getInviteId, accountId)
        );
        if (count > 0 || inviteState){
            // 当前 账号已经绑定过邀请人
            throw new ResultException("The current account already has an invitee, which cannot be filled in repeatedly");
        }
        // 当前账号未填写过邀请码 -- 则其可以进行 邀请码的填写
        String directInviterId = accountPromo.getAccountId();

        String utcTime = ZonedDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN));

        SanyiAccountInvite accountInvite = SanyiAccountInvite.builder()
                .inviteId(accountId)
                .directInviterId(directInviterId)
                .inviteTime(utcTime)
                .build();

        // TODO 完善 通知消息
        SanyiAccountMessage accountMessage = SanyiAccountMessage.builder()
                .accountId(directInviterId)
                .messageInfo("通知消息")
                .messageState(Boolean.FALSE)
                .messageType(MessageTypeEnum.INVITE.getCode())
                .build();

        createInvite(accountInvite, accountMessage);
    }

    private void createInvite(SanyiAccountInvite accountInvite, SanyiAccountMessage accountMessage)
    {
        // 邀请关系 建立
        accountInviteService.save(accountInvite);
        // 通知被邀请人
        messageService.save(accountMessage);
        // 被邀请人 推广数 + 1
        this.baseMapper.updateInviteNum(accountInvite.getDirectInviterId());
    }

    private void createAccount(SanyiThird sanyiThird, SanyiAccount sanyiAccount, SanyiAccountPromo accountPromo)
    {
        // 保存 推广码 对应信息
        sanyiPromoService.save(accountPromo);
        // 保存 第三方信息 -- 推广码-设备码-
        sanyiThirdService.save(sanyiThird);
        // 保存账号信息 -- 账号id, 头像昵称之类的
        this.baseMapper.insert(sanyiAccount);
    }

}
