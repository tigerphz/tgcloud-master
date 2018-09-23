package com.tiger.tgcloud.security;

import com.tiger.tgcloud.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: tiger
 * @date: 2018/8/6 12:57
 * @version: V1.0
 * @modified by:
 */
@Order
@Component
public class PermissionAuthorizeConfigProvider implements AuthorizeConfigProvider {

    /**
     * Config boolean.
     *
     * @param config the config
     * @return the boolean
     */
    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config
                .anyRequest()
                .access("@permissionService.hasPermission(authentication,request)");
        return true;
    }

}
