package com.tiger.tgcloud.security.core.authentication;

import com.tiger.tgcloud.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @description: 表单登录配置
 * @author: tiger
 * @date: 2018/8/4 16:17
 * @version: V1.0
 * @modified by:
 */
@Component
public class FormAuthenticationConfig {

    /**
     * The Pc authentication success handler.
     */
    protected final AuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     * The Pc authentication failure handler.
     */
    protected final AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * Instantiates a new Form authentication config.
     *
     * @param pcAuthenticationSuccessHandler the pc authentication success handler
     * @param pcAuthenticationFailureHandler the pc authentication failure handler
     */
    @Autowired
    public FormAuthenticationConfig(AuthenticationSuccessHandler pcAuthenticationSuccessHandler, AuthenticationFailureHandler pcAuthenticationFailureHandler) {
        this.authenticationSuccessHandler = pcAuthenticationSuccessHandler;
        this.authenticationFailureHandler = pcAuthenticationFailureHandler;
    }

    /**
     * Configure.
     *
     * @param http the http
     * @throws Exception the exception
     */
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);
    }

}