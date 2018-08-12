package com.tiger.tgcloud.security.core.properties;

import lombok.Data;

/**
 * @description: 认证服务器注册的第三方应用配置项
 * @author: tiger
 * @date: 2018/8/1 22:20
 * @version: V1.0
 * @modified by:
 */
@Data
public class OAuth2ClientProperties {

    /**
     * 第三方应用appId
     */
    private String clientId;
    /**
     * 第三方应用appSecret
     */
    private String clientSecret;
    /**
     * 针对此应用发出的token的有效时间
     */
    private int accessTokenValidateSeconds = 7200;

    private int refreshTokenValiditySeconds = 2592000;

    private String scope;
}
