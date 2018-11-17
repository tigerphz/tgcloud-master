package com.tiger.tgcloud;

import com.tiger.tgcloud.core.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/23 23:20
 * @version: V1.0
 * @modified by:
 */
@EnableCaching
@EnableHystrix
@EnableFeignClients
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
@Import(SwaggerConfiguration.class)
public class TgCloudUacApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(TgCloudUacApplication.class, args);
    }

}
