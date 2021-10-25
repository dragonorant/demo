package com.sanyicloud.sanyi.gateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanyicloud.sanyi.gateway.filter.SanyiRequestGlobalFilter;
import com.sanyicloud.sanyi.gateway.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 网关配置
 *
 * @author L.cm
 */
@Configuration(proxyBeanMethods = false)
public class GatewayConfiguration {

	@Bean
	public SanyiRequestGlobalFilter pigRequestGlobalFilter() {
		return new SanyiRequestGlobalFilter();
	}

	@Bean
	public GlobalExceptionHandler globalExceptionHandler(ObjectMapper objectMapper) {
		return new GlobalExceptionHandler(objectMapper);
	}
}
