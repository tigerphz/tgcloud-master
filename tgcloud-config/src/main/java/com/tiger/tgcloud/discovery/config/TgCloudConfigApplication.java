package com.tiger.tgcloud.discovery.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * The class tgcloud discovery application.
 *
 * @author tigerphz
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class TgCloudConfigApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(TgCloudConfigApplication.class, args);
    }
}
