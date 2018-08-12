package com.tiger.tgcloud.security.core.social.support;

/**
 * @description:
 * @author: tiger
 * @date: 2018/8/9 16:38
 * @version: V1.0
 * @modified by:
 */
public abstract class SocialProperties {

    /**
     * Application id.
     */
    private String appId;

    /**
     * Application secret.
     */
    private String appSecret;

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return this.appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

}
