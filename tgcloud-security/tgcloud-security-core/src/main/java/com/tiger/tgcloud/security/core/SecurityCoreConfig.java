package com.tiger.tgcloud.security.core;

import com.tiger.tgcloud.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: tiger
 * @date: 2018/8/3 23:47
 * @version: V1.0
 * @modified by:
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}