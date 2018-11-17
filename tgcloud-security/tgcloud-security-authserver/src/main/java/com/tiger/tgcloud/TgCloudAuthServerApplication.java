package com.tiger.tgcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * The class tgcloud authorization server application.
 *
 * @author tigerphz
 */
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class TgCloudAuthServerApplication {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(TgCloudAuthServerApplication.class, args);
    }
}
