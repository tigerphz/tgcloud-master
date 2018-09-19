package com.tiger.tgcloud.base.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/19 22:18
 * @version: V1.0
 * @modified by:
 */
@Configuration
@EnableConfigurationProperties(TgcloudProperties.class)
public class TgcloudCoreConfig {
}
