package com.tiger.tgcloud.base.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: tiger
 * @date: 18-1-19 下午2:56
 * @version: V1.0
 * @modified by:
 */
@ConfigurationProperties(prefix = "snowflakeid")
public class SnowflakeIdProperty {
    @Getter
    @Setter
    private long workerId;

    @Getter
    @Setter
    private long datacenterId;
}
