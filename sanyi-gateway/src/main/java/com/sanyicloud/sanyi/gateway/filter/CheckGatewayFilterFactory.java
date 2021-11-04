package com.sanyicloud.sanyi.gateway.filter;

import cn.hutool.core.text.CharSequenceUtil;
import com.sanyicloud.sanyi.common.core.constant.CacheConstants;
import com.sanyicloud.sanyi.common.core.exception.CheckedException;
import com.sanyicloud.sanyi.common.core.util.DateUtils;
import com.sanyicloud.sanyi.common.core.util.MD5Utils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

/**
 * by zhaowenyuan create 2021/10/26 09:45
 */
@Slf4j
@RequiredArgsConstructor
public class CheckGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    @Value("${server.servlet.context-path}")
    private String CONTEXT_PATH;

    private static final String HEAD_TOKEN_KEY = "sanyiToken";
    private static final String MUST_REQUEST_TIMESTAMP = "_request";
    private static final String MUST_REQUEST_RANDOM = "_random";
    private static final String MUST_REQUEST_SIGN = "_sign";

    @Autowired
    RedissonClient redissonClient;

    @Override
    public GatewayFilter apply(Object config) {
        return (this::filter);
    }

    private Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        LinkedHashSet<URI> originUrl = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        val path = originUrl.stream().findFirst().get().getPath().replace(CONTEXT_PATH, "");
        log.info("originUrl:{}", path);
        if (isIgnoreUrl(path)) {
            log.info("白名单:{}", path);
            return chain.filter(exchange);
        }
        // 校验是否 需要携带token
        if (!IgnoreUri.TOKEN_PATH.contains(path)){
            checkToken(exchange);
        }
        // 可以添加对于某些指定路径的拦截

        return chain.filter(exchange);
    }

    private void checkToken(ServerWebExchange exchange){
        HttpHeaders headers = exchange.getRequest().getHeaders();
        // 请求投 携带的 token
        String sanyiToken = headers.getFirst(HEAD_TOKEN_KEY);
        if (StringUtils.isEmpty(sanyiToken)){
            throw new CheckedException("鉴权失败");
        }
        // todo 从 redis 读取token ,判断token 的有效性
        RBucket<Object> bucket = redissonClient.getBucket("token:" + sanyiToken);
        if (bucket.isExists()){
            throw new CheckedException("鉴权失败");
        }

    }


    /**
     * 校验 请求是否携带 时间戳
     * @author ant
     * @date 2021/11/4 14:20
     */
    private void checkQuery(ServerWebExchange exchange){
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        // 接口请求的时间戳
        String _request = queryParams.getFirst(MUST_REQUEST_TIMESTAMP);
        if (CharSequenceUtil.isBlank(_request)) {
            throw new CheckedException("请求时间戳不能为空");
        }
        // 为 sanyicloud 时，不进行参数校验
        if (!_request.equals("sanyicloud")) {
            // 请求携带时间戳 -- 这个是客户端的，先出来，也就是他应该小
            long request_timestamp = Long.parseLong(_request);
            // UTC 时间的 时间戳 -- 这个是服务端的，后出来，他应该大
            long timestamp = DateUtils.getZoneOfMillis();
            if (request_timestamp > timestamp) {
                throw new CheckedException("请求非法");
            }
            log.info("timestamp:{},request_timestamp:{}", timestamp, request_timestamp);
            // 10 秒前的请求
            if (timestamp - request_timestamp > 10000) {
                throw new CheckedException("请求超时");
            }
            String _random = queryParams.getFirst(MUST_REQUEST_RANDOM);
            if (CharSequenceUtil.isBlank(_random)) {
                throw new CheckedException("请求随机字符串不能为空");
            }
            // 需要进行加密的
            String key = String.format(CacheConstants.PARAMS_CHECK_KEY, _request, _random);
            // 前端加密 信息
            String _sign = queryParams.getFirst(MUST_REQUEST_SIGN);

            String sign = MD5Utils.getMD5Digest(key);
            if (!sign.equals(_sign)) {
                throw new CheckedException("参数校验失败");
            }
        }
    }
    /**
     * 全路径匹配
     *
     * @param url 请求路径
     */
    private static boolean isIgnoreUrl(String url) {
        return IgnoreUri.FULL_PATH.contains(url) || IgnoreUri.FUZZY_PATH.stream().anyMatch(url::startsWith);
    }

    public static void main(String[] args) {
        long timestamp = DateUtils.getZoneOfMillis();
        String _request = String.valueOf(timestamp);
        System.out.println(_request);
        String _random = "123456";
        System.out.println(_random);
        String key = String.format(CacheConstants.PARAMS_CHECK_KEY, _request, _random);
        String md5Digest = MD5Utils.getMD5Digest(key);
        System.out.println(md5Digest);

    }
}
