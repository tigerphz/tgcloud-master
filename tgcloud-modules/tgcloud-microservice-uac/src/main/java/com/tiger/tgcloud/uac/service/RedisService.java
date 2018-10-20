package com.tiger.tgcloud.uac.service;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/20 21:01
 * @version: V1.0
 * @modified by:
 */
public interface RedisService {

    /**
     * Gets key.
     *
     * @param key the key
     *
     * @return the key
     */
    String getKey(String key);

    /**
     * Delete key.
     *
     * @param key the key
     */
    void deleteKey(String key);

    /**
     * Sets key.
     *
     * @param key   the key
     * @param value the value
     */
    void setKey(String key, String value);

    /**
     * Sets key.
     *
     * @param key     the key
     * @param value   the value
     * @param timeout the timeout
     * @param unit    the unit
     */
    void setKey(String key, String value, final long timeout, final TimeUnit unit);
}
