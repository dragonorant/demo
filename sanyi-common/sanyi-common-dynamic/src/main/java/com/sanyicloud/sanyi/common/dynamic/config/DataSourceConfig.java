package com.sanyicloud.sanyi.common.dynamic.config;

import com.zaxxer.hikari.HikariConfig;
import lombok.Data;

@Data
public class DataSourceConfig {
    private String dataSourceName;
    private HikariConfig hikari;
}
