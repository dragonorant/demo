package com.sanyicloud.gateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanyicloud.gateway.filter.SanyiRequestGlobalFilter;
import com.sanyicloud.gateway.handler.GlobalExceptionHandler;
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
	public SanyiRequestGlobalFilter sanyiRequestGlobalFilter() {
		return new SanyiRequestGlobalFilter();
	}

	@Bean
	public GlobalExceptionHandler globalExceptionHandler(ObjectMapper objectMapper) {
		return new GlobalExceptionHandler(objectMapper);
	}

}
