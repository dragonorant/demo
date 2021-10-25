package com.sanyicloud.sanyi.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Slf4j
@Component
public class CorsResponseHeaderFilter implements GlobalFilter, Ordered {
    @Override
    public int getOrder() {
        // 指定位于 NettyWriteResponseFilter 处理完响应体后移除重复 CORS 响应头
        return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER + 1;
    }
    @Override
    @SuppressWarnings("serial")
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.debug("CorsResponseHeaderFilter -->{}",System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.defer(() -> {
            exchange.getResponse().getHeaders().entrySet().stream()
                    .filter(kv -> (kv.getValue() != null && kv.getValue().size() > 1))
                    .filter(kv -> (kv.getKey().equals(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)
                            || kv.getKey().equals(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS)))
                    .forEach(kv -> kv.setValue(new ArrayList<String>() {{
                        add(kv.getValue().get(0));
                    }}));
            return chain.filter(exchange);
        }));
    }
}