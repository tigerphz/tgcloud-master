package com.tiger.tgcloud.security.feign;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The class Oauth 2 client properties.
 *
 * @author tgcloud.net @gmail.com
 */
@Data
@ConfigurationProperties(prefix = "tgcloud.oauth2.client")
public class OAuth2ClientProperties {
    private String id;
    private String accessTokenUrl;
    private String clientId;
    private String clientSecret;
    private String clientAuthenticationScheme;
}

