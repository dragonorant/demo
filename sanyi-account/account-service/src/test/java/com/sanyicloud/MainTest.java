package com.sanyicloud;

import cn.hutool.core.date.DatePattern;
import com.sanyicloud.account.entity.po.SanyiAccountInvite;
import com.sanyicloud.account.mapper.SanyiAccountMapper;
import com.sanyicloud.account.service.SanyiAccountInviteService;
import com.sanyicloud.account.service.SanyiAccountService;
import com.sanyicloud.account.service.SanyiThirdService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * by zhaowenyuan create 2022/1/25 11:05
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class MainTest {
    @Autowired
    SanyiThirdService sanyiThirdService;
    @Autowired
    SanyiAccountService sanyiAccountService;
    @Autowired
    SanyiAccountMapper accountMapper;
    @Autowired
    SanyiAccountInviteService accountInviteService;

    @Test
    public void test(){
//        log.info("get promo code");
//        val inviteBO = new InviteBO();
//        inviteBO.setDeviceNum("sdfdfhfgjklkjhgfds");
//        inviteBO.setDeviceType((short) 2);
//
//        inviteBO.setPromoCode("qyYVj2");
//
//        sanyiAccountService.inviteAccount(inviteBO);
        String utcTime = ZonedDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN));

        SanyiAccountInvite accountInvite = SanyiAccountInvite.builder()
                .inviteId("545277768874850509445")
                .directInviterId("directInviterId")
                .inviteTime(utcTime)
                .build();
        accountInviteService.save(accountInvite);
//        List<SanyiAccountInvite> list = accountInviteService.list();
//        log.info("{}",list);
    }

}
