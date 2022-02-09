package com.sanyicloud;

import com.sanyicloud.account.entity.po.SanyiThird;
import com.sanyicloud.account.service.SanyiThirdService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * by zhaowenyuan create 2022/1/25 11:05
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class MainTest {
    @Autowired
    SanyiThirdService sanyiThirdService;

    @Test
    public void test(){
        List<SanyiThird> list = sanyiThirdService.list();
        log.info("{}", list);
    }

}
