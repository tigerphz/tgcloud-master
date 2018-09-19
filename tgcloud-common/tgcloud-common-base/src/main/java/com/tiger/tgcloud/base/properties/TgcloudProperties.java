package com.tiger.tgcloud.base.properties;

import com.tiger.tgcloud.base.constant.GlobalConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/19 22:04
 * @version: V1.0
 * @modified by:
 */
@Data
@ConfigurationProperties(prefix = GlobalConstant.ROOT_PREFIX)
public class TgcloudProperties {
    private AsyncTaskProperties task = new AsyncTaskProperties();

    private SwaggerProperties swagger = new SwaggerProperties();

    private GaodeProperties gaode = new GaodeProperties();

    private JobProperties job = new JobProperties();
}
