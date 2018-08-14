package com.tiger.tgcloud.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description: The class Security properties.
 * @author: tiger
 * @date: 2018/8/1 22:20
 * @version: V1.0
 * @modified by:
 */
@ConfigurationProperties(prefix = "tgcloud.security")
@Data
public class SecurityProperties {

    /**
     * 浏览器环境配置
     */
    private BrowserProperties browser = new BrowserProperties();

    /**
     * 验证码配置
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();

    /**
     * 社交登录配置
     */
    private SocialProperties social = new SocialProperties();
    
    /**
     * OAuth2认证服务器配置
     */
    private OAuth2Properties oauth2 = new OAuth2Properties();

}
