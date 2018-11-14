package com.tiger.tgcloud.security.core.authorize;

import com.tiger.tgcloud.security.core.properties.SecurityConstants;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: tiger
 * @date: 2018/8/6 13:00
 * @version: V1.0
 * @modified by:
 */
@Component
@Order(Integer.MIN_VALUE)
public class PcAuthorizeConfigProviderImpl implements AuthorizeConfigProvider {

    /**
     * Config boolean.
     *
     * @param config the config
     * @return the boolean
     */
    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE,
                SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_OPENID,
                SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL,
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                "/actuator/**", "/druid/**", "/auth/**", "/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs").permitAll();
        return false;
    }

}
