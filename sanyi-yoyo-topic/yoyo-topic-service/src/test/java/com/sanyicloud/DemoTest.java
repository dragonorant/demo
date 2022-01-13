package com.sanyicloud;

import com.sanyicloud.sanyi.common.mybatis.mapper.DynamicTableNameMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * by zhaowenyuan create 2022/1/13 10:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest {

    @Autowired
    DynamicTableNameMapper dynamicTableNameMapper;

    @Test
    public void test(){
        int t_yoyo_topic_entry_1 = dynamicTableNameMapper.tableIsExist("t_yoyo_topic_entry_2");
        System.out.println(t_yoyo_topic_entry_1);
    }

}
