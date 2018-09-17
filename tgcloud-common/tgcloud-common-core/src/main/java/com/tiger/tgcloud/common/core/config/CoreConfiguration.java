package com.tiger.tgcloud.common.core.config;

import com.tiger.tgcloud.common.core.interceptor.SqlLogInterceptor;
import com.tiger.tgcloud.common.core.interceptor.TokenInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @description: 加载LWR规则.
 * @author: tiger
 * @date: 2018/9/14 22:40
 * @version: V1.0
 * @modified by:
 */
@Configuration
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


    @Bean
    @ConditionalOnMissingBean(HandlerInterceptor.class)
    @ConditionalOnProperty(prefix = "tgcloud.token.interceptor", name = "enable", havingValue = "true")
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }
}
