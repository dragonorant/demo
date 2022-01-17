package com.sanyicloud.sanyi.common.core.util;

import cn.hutool.crypto.digest.MD5;
import lombok.experimental.UtilityClass;

import java.math.BigInteger;

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
     * @param sign 签名
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

}
