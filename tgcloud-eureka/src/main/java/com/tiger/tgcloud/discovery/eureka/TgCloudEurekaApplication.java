package com.tiger.tgcloud.discovery.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * The class tgcloud eureka application.
 *
 * @author tigerphz
 */
@EnableEurekaServer
@SpringBootApplication
public class TgCloudEurekaApplication {

    @Configuration
    public static class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
            http.headers().frameOptions().disable();
            //注意：为了可以使用 http://${user}:${password}@${host}:${port}/eureka/ 这种方式登录,所以必须是httpBasic,如果是form方式,不能使用url格式登录
            http.authorizeRequests()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .httpBasic()
                    .and()
                    .csrf()
                    .disable();
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(TgCloudEurekaApplication.class, args);
    }
}
