package com.tiger.tgcloud.security.core.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.tiger.tgcloud.security.core.social.support.SocialProperties;

/**
 * QQ登录配置项
 *
 * @author
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QQProperties extends SocialProperties {

    /**
     * 第三方id，用来决定发起第三方登录的url，默认是 qq。
     */
    private String providerId = "qq";

}
