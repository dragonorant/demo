package com.sanyicloud.sanyi.gateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanyicloud.sanyi.gateway.filter.RequestBodyRewrite;
import com.sanyicloud.sanyi.gateway.filter.SanyiRequestGlobalFilter;
import com.sanyicloud.sanyi.gateway.handler.GlobalExceptionHandler;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

/**
 * 网关配置
 *
 * @author L.cm
 */
@Configuration(proxyBeanMethods = false)
public class GatewayConfiguration {

	@Bean
	public SanyiRequestGlobalFilter sanyiRequestGlobalFilter() {
		return new SanyiRequestGlobalFilter();
	}

	@Bean
	public GlobalExceptionHandler globalExceptionHandler(ObjectMapper objectMapper) {
		return new GlobalExceptionHandler(objectMapper);
	}

}
