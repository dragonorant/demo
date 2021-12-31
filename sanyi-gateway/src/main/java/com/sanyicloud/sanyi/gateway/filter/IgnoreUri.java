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
    // api路径 白名单
    private final List<String> FULL_PATH = new ArrayList<>();
    // 是否效验 token 如添加到 此集合中, 则不对 sanyiToken 的有效性进行效验 -- 即无法通过 sanyiToken 获取对应的 accountId
    private final List<String> TOKEN_PATH = new ArrayList<>();

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    static {
// ------------------------------------------------------------------
        FULL_PATH.add("/yoyo/account");
// ------------------------------------------------------------------
        TOKEN_PATH.add("/yoyo/account/login");
    }

    /**
     * api 白名单
     *
     * 路径 总共分为这些
     *
     * @param url 请求路径
     */
    public boolean isIgnoreUrl(String url) {
        for (String whitePath : FULL_PATH) {
            if (pathMatcher.match(whitePath, url)) {
                return true;
            }
        }
        return false;
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
