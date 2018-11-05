package com.tiger.tgcloud.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * @description:
 * @author: tiger
 * @date: 2018/11/5 14:21
 * @version: V1.0
 * @modified by:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class TgCloudZipkinApplication {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(TgCloudZipkinApplication.class, args);
    }
}
