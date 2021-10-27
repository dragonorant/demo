package com.sanyicloud.sanyi.gateway.filter;

import cn.hutool.core.text.CharSequenceUtil;
import com.sanyicloud.sanyi.common.core.constant.CacheConstants;
import com.sanyicloud.sanyi.common.core.exception.CheckedException;
import com.sanyicloud.sanyi.common.core.util.DateUtils;
import com.sanyicloud.sanyi.common.core.util.MD5Utils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;

/**
 * by zhaowenyuan create 2021/10/26 09:45
 */
@Slf4j
@RequiredArgsConstructor
public class CheckGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    private static final String MUST_REQUEST_TIMESTAMP = "_request";
    private static final String MUST_REQUEST_RANDOM = "_random";
    private static final String MUST_REQUEST_SIGN = "_sign";

    @Override
    public GatewayFilter apply(Object config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 可以添加对于某些指定路径的拦截
            checkRequest(request);

            return chain.filter(exchange);
        });
    }

    @SneakyThrows
    private void checkRequest(ServerHttpRequest request){
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        // 接口请求的时间戳
        String _request = queryParams.getFirst(MUST_REQUEST_TIMESTAMP);
        if (CharSequenceUtil.isBlank(_request)){
            throw new CheckedException("请求时间戳不能为空");
        }
        // 为 sanyicloud 时，不进行参数校验
        if (!_request.equals("sanyicloud")){
            // 请求携带时间戳 -- 这个是客户端的，先出来，也就是他应该小
            long request_timestamp = Long.parseLong(_request);
            // UTC 时间的 时间戳 -- 这个是服务端的，后出来，他应该大
            long timestamp = DateUtils.getZoneOfMillis();
            if (request_timestamp > timestamp){
                throw new CheckedException("请求非法");
            }
            log.info("timestamp:{},request_timestamp:{}",timestamp,request_timestamp);
            // 10 秒前的请求
            if (timestamp - request_timestamp > 10000){
                throw new CheckedException("请求超时");
            }
            String _random = queryParams.getFirst(MUST_REQUEST_RANDOM);
            if (CharSequenceUtil.isBlank(_random)){
                throw new CheckedException("请求随机字符串不能为空");
            }
            // 需要进行加密的
            String key = String.format(CacheConstants.PARAMS_CHECK_KEY,_request,_random);
            // 前端加密 信息
            String _sign = queryParams.getFirst(MUST_REQUEST_SIGN);

            String sign = MD5Utils.getMD5Digest(key);
            if (!sign.equals(_sign)){
                throw new CheckedException("参数校验失败");
            }
        }
    }
}
