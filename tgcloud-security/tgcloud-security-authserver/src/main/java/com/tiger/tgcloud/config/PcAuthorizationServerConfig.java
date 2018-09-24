package com.tiger.tgcloud.config;

/**
 * @description:
 * @author: tiger
 * @date: 2018/8/1 22:15
 * @version: V1.0
 * @modified by:
 */

import com.tiger.tgcloud.security.PcLogoutSuccessHandler;
import com.tiger.tgcloud.security.RestClientDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Pc authorization server properties.
 *
 * @author
 */
@Configuration
@EnableAuthorizationServer
public class PcAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private RestClientDetailsServiceImpl restClientDetailsService;

    /**
     * jwt需要的两个增强器之一：将uuid转换为jwt
     */
    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    /**
     * jwt需要的两个增强器之二：往jwt添加自定义信息
     */
    @Autowired(required = false)
    private TokenEnhancer jwtTokenEnhancer;

    /**
     * Configure.
     *
     * @param security the security
     * @throws Exception the exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        //你可以使用非对称加密算法来对Token进行签名，
        // Public Key公布在/oauth/token_key这个URL连接中，
        // 默认的访问安全规则是"denyAll()"，即在默认的情况下它是关闭的，
        // 你可以注入一个标准的 SpEL 表达式到 AuthorizationServerSecurityConfigurer
        // 这个配置中来将它开启（例如使用"permitAll()"来开启可能比较合适，
        // 因为它是一个公共密钥）。
        //url:/oauth/token_key,exposes public key for token verification if using JWT tokens
        security.tokenKeyAccess("permitAll()")
                //url:/oauth/check_token allow check token
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    /**
     * 功能：认证服务器会给哪些第三方应用发令牌
     * 在application.yml中配置有所有第三方应用的客户端信息信息
     * 会加载到内存中保存，用于对第三方应用进行认证
     *
     * @param clients the clients
     * @throws Exception the exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(restClientDetailsService);
    }

    /**
     * 配置TokenEndpoint 是  /oauth/token处理的入口点
     *
     * @param endpoints the endpoints
     * @throws Exception the exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);

        if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
            //增强器链
            TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancers = new ArrayList<>();
            enhancers.add(jwtTokenEnhancer);
            enhancers.add(jwtAccessTokenConverter);
            enhancerChain.setTokenEnhancers(enhancers);
            endpoints.tokenEnhancer(enhancerChain).accessTokenConverter(jwtAccessTokenConverter);
        }
    }

    /**
     * 退出时的处理策略配置
     *
     * @return logout success handler
     */
    @Bean
    public PcLogoutSuccessHandler logoutSuccessHandler() {
        return new PcLogoutSuccessHandler();
    }
}
