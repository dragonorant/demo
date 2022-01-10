package com.sanyicloud.sanyi.common.core.util;

import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * by zhaowenyuan create 2021/12/30 18:26
 * HttpServletRequest 获取工具类
 */
@UtilityClass
public class RequestUtils {
    // 字符串读取
    // 方法一
    public String ReadAsChars(HttpServletRequest request)
    {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try
        {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null)
            {
                sb.append(str);
            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != br)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

}
