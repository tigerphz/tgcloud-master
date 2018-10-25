package com.tiger.tgcloud.core.support;

import com.tiger.tgcloud.core.security.SnowflakeIdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/25 11:14
 * @version: V1.0
 * @modified by:
 */
public abstract class BaseService {
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    /**
     * The Logger.
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected long generateId() {
        return snowflakeIdWorker.nextId();
    }
}
