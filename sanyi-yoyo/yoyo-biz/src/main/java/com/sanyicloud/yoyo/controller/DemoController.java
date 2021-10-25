package com.sanyicloud.yoyo.controller;

import com.sanyicloud.sanyi.common.core.util.Result;
import com.sanyicloud.yoyo.service.TestService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * by zhaowenyuan create 2021/10/22 14:57
 */
@RestController
public class DemoController {

    @Resource
    TestService testService;

    @RequestMapping(value = "/test")
    public Result<String> test(@RequestBody Map<String,String> map){
        String params = map.get("params");
        return testService.test(params);
    }


}
