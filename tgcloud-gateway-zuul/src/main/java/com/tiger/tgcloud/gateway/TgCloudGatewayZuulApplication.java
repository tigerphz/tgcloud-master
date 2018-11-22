package com.tiger.tgcloud.gateway;

import com.didispace.swagger.butler.EnableSwaggerButler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/14 20:36
 * @version: V1.0
 * @modified by:
 */
@EnableEurekaClient
@EnableDiscoveryClient
@EnableZuulProxy
@EnableOAuth2Sso
@EnableHystrix
@EnableSwaggerButler
@SpringBootApplication
public class TgCloudGatewayZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(TgCloudGatewayZuulApplication.class, args);
    }
}
