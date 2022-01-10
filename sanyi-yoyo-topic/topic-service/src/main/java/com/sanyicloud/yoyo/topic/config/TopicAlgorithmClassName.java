package com.sanyicloud.yoyo.topic.config;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

/**
 * by zhaowenyuan create 2021/12/31 18:34
 * 自定义的 分片算法
 */
@Slf4j
@Getter
@Setter
public class TopicAlgorithmClassName implements StandardShardingAlgorithm<Comparable<?>> {


    private String tableNamePrefix;

    @Override
    public void init() {
        tableNamePrefix = getShardingTableNamePrefix();
    }

    /**
     * 自定义 类分片算法没法添加 props
     * @return
     */
    private String getShardingTableNamePrefix() {
        return "yoyo_topic_entry_";
    }

    /**
     * 精确分片
     */
    @Override
    public String doSharding(final Collection<String> availableTargetNames,final PreciseShardingValue<Comparable<?>> shardingValue) {
        String value = String.valueOf(shardingValue.getValue());
        log.info("availableTargetNames : {}, shardingValue : {}", availableTargetNames, shardingValue);
        for (String tableName : availableTargetNames) {
            if (tableNamePrefix.startsWith(tableName)){
                return tableNamePrefix + value;
            }
        }
        return null;
    }

    /**
     * 区间分片
     */
    @Override
    public Collection<String> doSharding(final Collection<String> availableTargetNames,final RangeShardingValue<Comparable<?>> shardingValue) {
        return availableTargetNames;
    }

    @Override
    public String getType() {
        return "TOPIC";
    }
}
