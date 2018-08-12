package com.tiger.tgcloud.security.core.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 图片验证码配置项
 * @author: tiger
 * @date: 2018/8/1 22:29
 * @version: V1.0
 * @modified by:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImageCodeProperties extends SmsCodeProperties {

    /**
     * Instantiates a new Image code properties.
     */
    ImageCodeProperties() {
        super.setLength(4);
    }
}
