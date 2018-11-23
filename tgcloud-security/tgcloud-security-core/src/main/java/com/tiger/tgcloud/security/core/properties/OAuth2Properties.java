package com.tiger.tgcloud.security.core.properties;

import lombok.Data;

/**
 * @description:
 * @author: tiger
 * @date: 2018/8/1 22:22
 * @version: V1.0
 * @modified by:
 */
@Data
public class OAuth2Properties {

    /**
     * 使用jwt时为token签名的秘钥
     */
    private String jwtSigningKey = "tgcloud";

    private int accessTokenValidateSeconds = 7200;

    private int refreshTokenValiditySeconds = 2592000;

    private String scope;

    /**
     * 客户端配置
     */
    private OAuth2ClientProperties[] clients = {};

}
