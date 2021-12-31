package com.sanyicloud.sanyi.common.core.util;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import lombok.experimental.UtilityClass;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * by zhaowenyuan create 2021/12/29 18:00
 */
@UtilityClass
public class IdUtils {

    private final String sign = "sanyi_cloud_sign";

    /**
     * 测试 一千万 条数据均没有任何影响 -- 再多电脑扛不住了
     * 生成 19位 数值类型 id
     * 字符串转数值类型 id
     * @param str 设备 id
     * @param str sign
     * @return 根据设备码生成的 数值
     */
    public String strConvertNum(String str,String sign) {
        MD5 md5 = new MD5();
        BigInteger bigInteger = new BigInteger(1, md5.digest(str));
        return Math.abs(bigInteger.longValue()) + "";
    }

    public String strConvertNum(String str){
        return strConvertNum(str, sign);
    }

    /**
     * 校对
     */
    private class  Proof{
        private char startChar = 'A';
        private char endChar = 'z';
        private int offset = endChar - startChar + 1;
        private int dup = 0;
        private int count = 0;
        private HashMap map = new HashMap();
        private void tryBit(char[] chars, int i) {
            if (map.size() <= 8000000){
                for (char j = startChar; j <= endChar; j++) {
                    chars[i - 1] = j;
                    if (i > 1)
                        tryBit(chars, i - 1);
                    else
                        test(chars);
                }
            }
        }

        private void test(char[] chars) {

            String str = new String(chars);
            String hash = IdUtils.strConvertNum(str);
            if (map.containsKey(hash)) {
                String s = (String) map.get(hash);
                if (!s.equals(str)) {
                    dup++;
                    System.out.println(s + ":" + str);
                }
            } else {
                map.put(hash, str);
                System.out.println(++count);
            }
        }
    }

}
