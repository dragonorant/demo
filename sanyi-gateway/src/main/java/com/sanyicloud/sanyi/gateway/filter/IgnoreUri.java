package com.sanyicloud.sanyi.gateway.filter;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * by zhaowenyuan create 2021/11/4 10:24
 */
@Slf4j
public class IgnoreUri {
    // 全路径 白名单
    public static final List<String> FULL_PATH = new ArrayList<>();
    // ** 白名单
    public static final List<String> FUZZY_PATH = new ArrayList<>();
    /**
     * 不校验 携带 token的 path
     */
    public static final List<String> TOKEN_PATH = new ArrayList<>();

    static {
// ------------------------QUERY_PATH-----------------------------------------
        // 账号
        TOKEN_PATH.add("/yoyo/v1/topic/account");


// ----------------------------FULL_PATH-------------------------------------


// ----------------------------FUZZY_PATH-------------------------------------
    }

}
