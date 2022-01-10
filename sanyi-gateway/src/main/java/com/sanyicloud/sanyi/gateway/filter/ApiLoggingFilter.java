package com.sanyicloud.sanyi.gateway.filter;

import com.sanyicloud.sanyi.common.core.constant.CommonConstants;
import com.sanyicloud.sanyi.common.core.util.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author zhangran
 * @date 2021/7/13
 * <p>
 * 全局拦截器，作用所有的微服务
 * <p>
 * 1. 对请求的API调用过滤，记录接口的请求时间，方便日志审计、告警、分析等运维操作 2. 后期可以扩展对接其他日志系统
 * <p>
 */
@Slf4j
@Component
public class ApiLoggingFilter implements GlobalFilter, Ordered {

	private static final String START_TIME = "startTime";

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		exchange.getAttributes().put(START_TIME, System.currentTimeMillis());
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			if (log.isInfoEnabled()) {
				String info = String.format("Method:{%s} Host:{%s} Path:{%s} Query:{%s}",
						exchange.getRequest().getMethod().name(), exchange.getRequest().getURI().getHost(),
						exchange.getRequest().getURI().getPath(), exchange.getRequest().getQueryParams());
				ServerHttpRequest request = exchange.getRequest();
				String sanyiToken = request.getHeaders().getFirst(CommonConstants.HEAD_TOKEN_KEY);
				Long startTime = exchange.getAttribute(START_TIME);
				if (startTime != null) {
				Long executeTime = (System.currentTimeMillis() - startTime);
				String ip = IPUtils.getIpAddress(request);
				// 请求路径
				String api = exchange.getRequest().getURI().getRawPath();
				HttpStatus statusCode = exchange.getResponse().getStatusCode();
				// 状态码
				int code = statusCode != null ? statusCode.value() : 500;
				// 此处 添加 到 plumelog 中, 进行数据统计
					log.info("来自IP地址: {}的请求接口: {}, 请求信息: {},请求人: {} , 响应状态码: {}, 请求耗时: {}ms", ip, api, info ,sanyiToken ,code, executeTime);
				}
			}
		}));
	}

	@Override
	public int getOrder() {
		return 1;
	}

}
