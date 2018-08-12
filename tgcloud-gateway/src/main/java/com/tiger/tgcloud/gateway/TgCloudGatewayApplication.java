package com.tiger.tgcloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * The class tgcloud gateway application.
 *
 * @author tigerphz
 */
@SpringBootApplication
@EnableEurekaClient
@EnableOAuth2Sso
public class TgCloudGatewayApplication {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(TgCloudGatewayApplication.class, args);
    }
}
