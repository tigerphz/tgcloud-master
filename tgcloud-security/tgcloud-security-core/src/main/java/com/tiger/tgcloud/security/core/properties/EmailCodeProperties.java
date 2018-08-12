package com.tiger.tgcloud.security.core.properties;

import lombok.Data;

/**
 * @description:
 * @author: tiger
 * @date: 2018/8/1 22:30
 * @version: V1.0
 * @modified by:
 */
@Data
public class EmailCodeProperties {

    /**
     * 过期时间
     */
    private int expireIn = 60 * 60 * 24;
    /**
     * 要拦截的url，多个url用逗号隔开，ant pattern
     */
    private String url;

}

