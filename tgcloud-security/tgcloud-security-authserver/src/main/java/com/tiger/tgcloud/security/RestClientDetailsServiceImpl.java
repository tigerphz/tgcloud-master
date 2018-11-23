package com.tiger.tgcloud.security;

import com.tiger.tgcloud.dmc.api.model.domain.MicroServiceInfo;
import com.tiger.tgcloud.mapper.MicroServiceMapper;
import com.tiger.tgcloud.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/8/1 22:18
 * @version: V1.0
 * @modified by:
 */
@Slf4j
@Component("restClientDetailsService")
public class RestClientDetailsServiceImpl implements ClientDetailsService {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MicroServiceMapper microServiceMapper;

    private ClientDetailsService clientDetailsService;

    /**
     * Init.
     * Oauth支持的5类 grant_type 及说明
     * authorization_code 授权码模式(即先登录获取code,再获取token)
     * password 密码模式(将用户名,密码传过去,直接获取token)
     * client_credentials 客户端模式(无用户,用户向客户端注册,然后客户端以自己的名义向’服务端’获取资源)
     * implicit 简化模式(在redirect_uri 的Hash传递token; Auth客户端运行在浏览器中,如JS,Flash)
     * refresh_token 刷新access_token
     */
    @PostConstruct
    public void init() {
        InMemoryClientDetailsServiceBuilder builder = new InMemoryClientDetailsServiceBuilder();
//        if (ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())) {
//            for (OAuth2ClientProperties client : securityProperties.getOauth2().getClients()) {
//                builder.withClient(client.getClientId())
//                        //必须密码加密
//                        .secret(passwordEncoder.encode(client.getClientSecret()))
//                        .authorizedGrantTypes("refresh_token", "password", "client_credentials")
//                        //发出令牌有效期
//                        .accessTokenValiditySeconds(client.getAccessTokenValidateSeconds())
//                        .refreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds())
//                        //权限
//                        .scopes(client.getScope());
//            }
//        }

        List<MicroServiceInfo> microServiceInfos = loadMicroServices();
        if (!CollectionUtils.isEmpty(microServiceInfos)) {
            for (MicroServiceInfo client : microServiceInfos) {
                builder.withClient(client.getClientId())
                        //必须密码加密
                        .secret(passwordEncoder.encode(client.getClientSecret()))
                        .authorizedGrantTypes("refresh_token", "password", "client_credentials")
                        //发出令牌有效期
                        .accessTokenValiditySeconds(securityProperties.getOauth2().getAccessTokenValidateSeconds())
                        .refreshTokenValiditySeconds(securityProperties.getOauth2().getRefreshTokenValiditySeconds())
                        //权限
                        .scopes(securityProperties.getOauth2().getScope());
            }
        }

        try {
            clientDetailsService = builder.build();
        } catch (Exception e) {
            log.error("init={}", e.getMessage(), e);
        }
    }

    /**
     * Load client by client id client details.
     *
     * @param clientId the client id
     * @return the client details
     * @throws ClientRegistrationException the client registration exception
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientDetailsService.loadClientByClientId(clientId);
    }

    /**
     * 加载微服务client信息
     *
     * @return
     */
    private List<MicroServiceInfo> loadMicroServices() {
        log.info("load client from db");

        Example example = new Example(MicroServiceInfo.class);
        example.createCriteria().andEqualTo("status", 0)
                .andEqualTo("isAuth", true);
        List<MicroServiceInfo> microServiceInfos = microServiceMapper.selectByExample(example);

        return microServiceInfos;
    }
}
