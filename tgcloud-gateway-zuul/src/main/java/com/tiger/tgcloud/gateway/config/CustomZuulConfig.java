package com.tiger.tgcloud.gateway.config;

import com.tiger.tgcloud.gateway.mapper.MicroServiceMapper;
import com.tiger.tgcloud.gateway.route.CustomDiscoveryClientRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.discovery.ServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 动态加载zuul路由配置
 * @author: tiger
 * @date: 2018/11/21 11:21
 * @version: V1.0
 * @modified by:
 */
@Configuration
public class CustomZuulConfig {
    @Autowired(required = false)
    private Registration registration;

    @Autowired
    private DiscoveryClient discovery;

    @Autowired
    private ServiceRouteMapper serviceRouteMapper;

    @Autowired
    ZuulProperties zuulProperties;

    @Autowired
    ServerProperties server;

    @Autowired
    MicroServiceMapper microServiceMapper;

    @Bean
    public DiscoveryClientRouteLocator discoveryRouteLocator() {
        CustomDiscoveryClientRouteLocator routeLocator = new CustomDiscoveryClientRouteLocator(this.server.getServlet().getServletPrefix(), this.discovery, this.zuulProperties,
                this.serviceRouteMapper, this.registration);
        routeLocator.setMicroServiceMapper(microServiceMapper);

        return routeLocator;
    }
}
