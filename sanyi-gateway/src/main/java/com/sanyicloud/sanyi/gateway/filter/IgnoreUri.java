package com.sanyicloud.sanyi.gateway.filter;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * by zhaowenyuan create 2021/11/4 10:24
 */
@Slf4j
@UtilityClass
public class IgnoreUri {
    // 是否效验 token 如添加到 此集合中, 则不对 sanyiToken 的有效性进行效验 -- 即无法通过 sanyiToken 获取对应的 accountId
    private final List<String> TOKEN_PATH = new ArrayList<>();

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    static {
        TOKEN_PATH.add("/yoyo/account/v1/login");
    }

    public boolean tokenUrl(String url){
        for (String whitePath : TOKEN_PATH) {
            if (pathMatcher.match(whitePath, url)) {
                return true;
            }
        }
        return false;
    }

}
