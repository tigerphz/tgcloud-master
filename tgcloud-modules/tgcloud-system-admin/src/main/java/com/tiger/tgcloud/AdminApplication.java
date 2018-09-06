package com.tiger.tgcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableCaching
@EnableHystrix
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EnableTransactionManagement
@RestController
public class AdminApplication {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

    @GetMapping(value = "hello")
    public String hello() {
        return "hello world";
    }
}
