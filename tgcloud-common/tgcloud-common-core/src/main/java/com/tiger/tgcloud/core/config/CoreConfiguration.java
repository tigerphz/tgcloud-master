package com.tiger.tgcloud.core.config;

import com.tiger.tgcloud.base.properties.SnowflakeIdProperty;
import com.tiger.tgcloud.core.interceptor.JwtTokenInterceptor;
import com.tiger.tgcloud.core.interceptor.RedisTokenInterceptor;
import com.tiger.tgcloud.core.interceptor.SqlLogInterceptor;
import com.tiger.tgcloud.core.interceptor.TokenInterceptor;
import com.tiger.tgcloud.core.security.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

/**
 * @description: 加载LWR规则.
 * @author: tiger
 * @date: 2018/9/14 22:40
 * @version: V1.0
 * @modified by:
 */
@Configuration
@EnableConfigurationProperties(SnowflakeIdProperty.class)
public class CoreConfiguration {
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public SqlLogInterceptor sqlLogInterceptor() {
        return new SqlLogInterceptor();
    }

    @Autowired
    private SnowflakeIdProperty snowflakeIdProperty;

    @Bean
    @Scope("singleton")
    public SnowflakeIdWorker getSnowflakeIdWorker() {
        return new SnowflakeIdWorker(snowflakeIdProperty.getWorkerId(), snowflakeIdProperty.getDatacenterId());
    }

    @Bean
    @ConditionalOnMissingBean(TokenInterceptor.class)
    @ConditionalOnExpression("${tgcloud.token.interceptor.enable:true} && '${tgcloud.oauth2.client.tokenStore}'.equals('redis')")
    public TokenInterceptor redisTokenInterceptor() {
        return new RedisTokenInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean(TokenInterceptor.class)
    @ConditionalOnExpression("${tgcloud.token.interceptor.enable:true} && '${tgcloud.oauth2.client.tokenStore}'.equals('jwt')")
    public TokenInterceptor jwtTokenInterceptor() {
        return new JwtTokenInterceptor();
    }
}
