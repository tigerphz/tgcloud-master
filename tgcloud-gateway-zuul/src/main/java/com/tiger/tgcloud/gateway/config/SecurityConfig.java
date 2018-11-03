package com.tiger.tgcloud.gateway.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/14 20:30
 * @version: V1.0
 * @modified by:
 */
@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Configure.
     *
     * @param http the http
     * @throws Exception the exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //X-Frame-Options: DENY     //表示该页面不允许在 frame 中展示，即便是在相同域名的页面中嵌套也不允许。。
        //X-Frame-Options: SAMEORIGIN    //表示该页面可以在相同域名页面的 frame 中展示。
        //X-Frame-Options: ALLOW-FROM http://caibaojian.com/   //表示该网页只能放在http://caibaojian.com//网页架设的iFrame内。
        http.headers().frameOptions().disable()
                .and().csrf().disable();
    }

}
