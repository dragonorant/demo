package com.sanyicloud.sanyi.common.dynamic.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix="spring.dynamic-datasource")
class SeparatedDataSourceConfig {
    List<DataSourceConfig> masters;
    List<DataSourceConfig> slaves;
}
