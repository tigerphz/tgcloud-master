package com.tiger.tgcloud.security.core.social.support;

import lombok.Data;

/**
 * @description:
 * @author: tiger
 * @date: 2018/8/9 16:38
 * @version: V1.0
 * @modified by:
 */
@Data
public abstract class SocialProperties {

    /**
     * Application id.
     */
    private String appId;

    /**
     * Application secret.
     */
    private String appSecret;

}
