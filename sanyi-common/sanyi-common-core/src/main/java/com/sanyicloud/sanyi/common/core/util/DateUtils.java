package com.sanyicloud.sanyi.common.core.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * by zhaowenyuan create 2021/10/26 14:52
 * 时间工具类
 */
public class DateUtils {

    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public final static String ISO_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public final static String DATE_PATTERN_2 = "yyyyMMdd";

    public final static String DEFAULT_TIME_ZONE = "GMT";

    /**
     * 获取 指定时区的时间戳
     * @author ant
     * @date 2021/10/26 15:19
     */
    public static long getZoneOfMillis(ZoneOffset zoneOffset){
        LocalDateTime now = LocalDateTime.now(zoneOffset);
        Date from = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        return from.getTime();
    }
    /**
     * 获取 UTC 时间戳
     * @author ant
     * @date 2021/10/26 15:20
     * @return long
     */
    public static long getZoneOfMillis(){
        return getZoneOfMillis(ZoneOffset.UTC);
    }
}
