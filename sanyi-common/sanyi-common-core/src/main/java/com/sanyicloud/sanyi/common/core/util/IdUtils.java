package com.sanyicloud.sanyi.common.core.util;

import cn.hutool.crypto.digest.MD5;
import lombok.experimental.UtilityClass;

import java.math.BigInteger;

/**
 * by zhaowenyuan create 2021/12/29 18:00
 */
@UtilityClass
public class IdUtils {

    private final String sign = "sane_cloud_sign";
    private final static MD5 md5 = new MD5();

    /**
     * 测试 一千万 条数据均没有任何影响 -- 再多电脑扛不住了
     * 生成 19位 数值类型 id
     * 字符串转数值类型 id
     * @param str 设备 id
     * @param sign 签名
     * @return 根据设备码生成的 数值
     */
    public static String strConvertNum(String str,String sign) {
        BigInteger bigInteger = new BigInteger(1, md5.digest(str + ":" + sign));
        return Math.abs(bigInteger.longValue()) + "";
    }

    public String strConvertNum(String str){
        return strConvertNum(str, sign);
    }

    /**
     * 获取 短链
     */
    public String[] shortNum(String str)
    {
        // 可以自定义生成 MD5 加密字符传前的混合 KEY
        // 要使用生成 URL 的字符
        String[] chars = new String[] { "a" , "b" , "c" , "d" , "e" , "f" , "g" , "h" ,
                "i" , "j" , "k" , "l" , "m" , "n" , "o" , "p" , "q" , "r" , "s" , "t" ,
                "u" , "v" , "w" , "x" , "y" , "z" , "0" , "1" , "2" , "3" , "4" , "5" ,
                "6" , "7" , "8" , "9" , "A" , "B" , "C" , "D" , "E" , "F" , "G" , "H" ,
                "I" , "J" , "K" , "L" , "M" , "N" , "O" , "P" , "Q" , "R" , "S" , "T" ,
                "U" , "V" , "W" , "X" , "Y" , "Z"
        };
        String[] resUrl = new String[4];
        for ( int i = 0; i < 4; i++) {
            // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
            String sTempSubString = md5.digestHex(sign + str).substring(i * 8, i * 8 + 8);
            // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界
            long lHexLong = 0x3FFFFFFF & Long.parseLong (sTempSubString, 16);
            StringBuilder outChars = new StringBuilder();
            for ( int j = 0; j < 6; j++) {
                // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
                long index = 0x0000003D & lHexLong;
                // 把取得的字符相加
                outChars.append(chars[(int) index]);
                // 每次循环按位右移 5 位
                lHexLong = lHexLong >> 5;
            }
            // 把字符串存入对应索引的输出数组
            resUrl[i] = outChars.toString();
        }
        return resUrl;
    }

}
