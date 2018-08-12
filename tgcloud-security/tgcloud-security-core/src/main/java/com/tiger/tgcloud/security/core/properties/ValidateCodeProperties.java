package com.tiger.tgcloud.security.core.properties;

import lombok.Data;

/**
 * @description: 验证码配置
 * @author: tiger
 * @date: 2018/8/1 22:24
 * @version: V1.0
 * @modified by:
 */
@Data
public class ValidateCodeProperties {

    /**
     * 图片验证码配置
     */
    private ImageCodeProperties image = new ImageCodeProperties();
    /**
     * 短信验证码配置
     */
    private SmsCodeProperties sms = new SmsCodeProperties();
    /**
     * 邮箱验证码配置
     */
    private EmailCodeProperties email = new EmailCodeProperties();

}
