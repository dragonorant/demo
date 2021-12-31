package com.sanyicloud.account.controller.v1;

import com.sanyicloud.account.entity.bo.AccountBO;
import com.sanyicloud.account.entity.po.YoyoAccount;
import com.sanyicloud.account.service.YoyoAccountService;
import com.sanyicloud.sanyi.common.core.util.IdUtils;
import com.sanyicloud.sanyi.common.core.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/**
 * by zhaowenyuan create 2021/11/4 12:12
 */
@Slf4j
@RestController
public class AccountController {
    @Autowired
    YoyoAccountService yoyoAccountService;

    @GetMapping(value = "/token")
    public Result getAccountByToken(String token)
    {
        log.info("token->{}",token);
        return null;
    }

    /**
     * 设备号登录 ｜ 注册 存在即登录, 不存在即注册
     * @author ant
     * @date 2021/11/4 12:16
     * @param accountBO 账号所需设备号
     * @return Result
     */
    @PostMapping(value = "/login")
    public Result login(@RequestBody @Valid AccountBO accountBO)
    {
        return yoyoAccountService.login(accountBO);
    }

    @RequestMapping(value = "/demo")
    public void demo(HttpServletRequest request, @RequestParam String accountId, MultipartFile file){
        Enumeration<String> parameterNames = request.getParameterNames();
        String s = ReadAsChars(request);
        System.out.println(s);
    }

    // 字符串读取
    // 方法一
    public static String ReadAsChars(HttpServletRequest request)
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

    public static void main(String[] args) {
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        String query = "?startDate=" + "2021-12-10" +"&endDate=" + "2021-12-15";
//        Request request = new Request.Builder()
//                .url("http://localhost:4005/yoyo/account/v1/demo")
//                .get()
//                .addHeader("Content-Type", "application/json")
//                .build();
//        try {
//            Response response = client.newCall(request).execute();
//            log.info("response : {}", response);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        String s = IdUtils.strConvertNum("s");
        System.out.println(s);
        System.out.println(s.length());
    }

}
