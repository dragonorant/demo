package com.sanyicloud.sanyi.common.core.util;

import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * by zhaowenyuan create 2021/12/30 11:42
 */
@Slf4j
@UtilityClass
public class TokenUtils {
    private final String TOKEN_SIGN = "sane_cloud_token";

    public String decryptStr(String sanyiToken){
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, TOKEN_SIGN.getBytes(StandardCharsets.UTF_8));
        try {
            return aes.decryptStr(sanyiToken);
        }catch (Exception e){
        }
        return null;
    }

}
