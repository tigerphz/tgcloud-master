package com.tiger.tgcloud.security.core.properties;

/**
 * @description: 认证成功后的响应方式
 * @author: tiger
 * @date: 2018/8/1 22:27
 * @version: V1.0
 * @modified by:
 */
public enum LoginResponseType {
    /**
     * 跳转
     */
    REDIRECT,
    /**
     * 返回json
     */
    JSON
}