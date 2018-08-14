
package com.tiger.tgcloud.security.core.social.qq.config;

import com.tiger.tgcloud.security.core.properties.QQProperties;
import com.tiger.tgcloud.security.core.properties.SecurityProperties;
import com.tiger.tgcloud.security.core.social.qq.connet.QQConnectionFactory;
import com.tiger.tgcloud.security.core.social.support.SocialAutoConfigurerAdapter;
import com.tiger.tgcloud.security.core.social.view.PcConnectView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

/**
 * The class Qq auto config.
 *
 * @author
 */
@Configuration
@ConditionalOnProperty(prefix = "tgcloud.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    private final SecurityProperties securityProperties;

    @Autowired
    public QQAutoConfig(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    /**
     * Create connection factory connection factory.
     *
     * @return the connection factory
     */
    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
    }

    /**
     * QQ connected view view. connect/qqConnect：解绑视图  connect/qqConnected：绑定视图
     *
     * @return the view
     */
    @Bean({"connect/qqConnect", "connect/qqConnected"})
    @ConditionalOnMissingBean(name = "qqConnectedView")
    public View qqConnectedView() {
        return new PcConnectView();
    }
}
