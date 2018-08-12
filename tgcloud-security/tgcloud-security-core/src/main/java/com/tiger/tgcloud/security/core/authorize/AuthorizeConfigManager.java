package com.tiger.tgcloud.security.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @description: 授权信息管理器
 * 用于收集系统中所有 AuthorizeConfigProvider 并加载其配置
 * @author: tiger
 * @date: 2018/8/6 12:58
 * @version: V1.0
 * @modified by:
 */
public interface AuthorizeConfigManager {

    /**
     * Config.
     *
     * @param config the config
     */
    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);

}
