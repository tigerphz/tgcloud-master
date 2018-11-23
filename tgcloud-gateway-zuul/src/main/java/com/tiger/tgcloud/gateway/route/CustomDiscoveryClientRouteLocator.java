package com.tiger.tgcloud.gateway.route;

import com.tiger.tgcloud.dmc.api.model.domain.MicroServiceInfo;
import com.tiger.tgcloud.gateway.mapper.MicroServiceMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.discovery.ServiceRouteMapper;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 动态加载zuul路由配置
 * @author: tiger
 * @date: 2018/11/21 11:04
 * @version: V1.0
 * @modified by:
 */
@Slf4j
public class CustomDiscoveryClientRouteLocator extends DiscoveryClientRouteLocator {
    private MicroServiceMapper microServiceMapper;

    private ZuulProperties properties;

    private Map<String, ZuulProperties.ZuulRoute> routesdb;

    public void setMicroServiceMapper(MicroServiceMapper microServiceMapper) {
        this.microServiceMapper = microServiceMapper;
    }

    public CustomDiscoveryClientRouteLocator(String servletPath, DiscoveryClient discovery, ZuulProperties properties,
                                             ServiceRouteMapper serviceRouteMapper, ServiceInstance localServiceInstance) {
        super(servletPath, discovery, properties, serviceRouteMapper, localServiceInstance);
        this.properties = properties;
    }

    @Override
    protected LinkedHashMap<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<>();
        //从application.properties中加载路由信息
        routesMap.putAll(super.locateRoutes());

        if (CollectionUtils.isEmpty(routesdb)) {
            //从db中加载路由信息
            loadLocateRoutesFromDB();
        }

        routesMap.putAll(routesdb);

        return routesMap;
    }

    public void loadLocateRoutesFromDB() {
        log.info("load zuul route info from db");

        Map<String, ZuulProperties.ZuulRoute> routes = new LinkedHashMap<>();

        //从数据库获取路由并缓存
        List<ZuulRouteBO> results = getZuulRouteFromDB();

        for (ZuulRouteBO result : results) {
            if (StringUtils.isEmpty(result.getPath())) {
                continue;
            }
            if (StringUtils.isEmpty(result.getServiceId()) && StringUtils.isEmpty(result.getUrl())) {
                continue;
            }
            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            try {
                BeanUtils.copyProperties(result, zuulRoute);
            } catch (Exception e) {
                log.error("=============load zuul route info from db with error==============", e);
            }
            routes.put(zuulRoute.getPath(), zuulRoute);
        }

        //优化一下配置
        LinkedHashMap<String, ZuulProperties.ZuulRoute> values = new LinkedHashMap<>();
        for (Map.Entry<String, ZuulProperties.ZuulRoute> entry : routes.entrySet()) {
            String path = entry.getKey();
            // Prepend with slash if not already present.
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (StringUtils.hasText(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            values.put(path, entry.getValue());
        }

        routesdb = values;
    }

    private List<ZuulRouteBO> getZuulRouteFromDB() {
        Example example = new Example(MicroServiceInfo.class);
        example.createCriteria().andEqualTo("status", 0)
                .andEqualTo("isRoute", true);
        List<MicroServiceInfo> microServiceInfos = microServiceMapper.selectByExample(example);

        List<ZuulRouteBO> results = microServiceInfos.stream().map(x -> {
            ZuulRouteBO zuulRouteBO = new ZuulRouteBO();
            zuulRouteBO.setId(x.routeName);
            zuulRouteBO.setPath(x.getBaseUrl());
            zuulRouteBO.setServiceId(x.serviceCode);
            return zuulRouteBO;
        }).collect(Collectors.toList());

        return results;
    }

    @Data
    public static class ZuulRouteBO implements Serializable {
        private static final long serialVersionUID = -1419588914942310377L;

        /**
         * The ID of the route (the same as its map key by default).
         * 路由的唯一编号  同时也默认为 装载路由的容器的Key 用来标识映射的唯一性 重要.
         */
        private String id;

        /**
         * The path (pattern) for the route, e.g. /foo/**.
         * 路由的规则 /foo/**.
         */
        private String path;

        /**
         * The service ID (if any) to map to this route. You can specify a physical URL or
         * a service, but not both.
         * 服务实例ID（如果有的话）来映射到此路由 你可以指定一个服务或者url 但是不能两者同时对于一个key来配置
         */
        private String serviceId;

        /**
         * A full physical URL to map to the route. An alternative is to use a service ID
         * and service discovery to find the physical address.
         * 就是上面提到的url
         */
        private String url;

        /**
         * Flag to determine whether the prefix for this route (the path, minus pattern
         * patcher) should be stripped before forwarding.
         * 路由前缀是否在转发开始前被删除 默认是删除
         * 举个例子 你实例的实际调用是http://localhost:8002/user/info
         * 如果你路由设置该实例对应的path 为 /api/v1/**  那么 通过路由调用
         * http://ip:port/api/v1/user/info
         * 当为true 转发到 http://localhost:8002/user/info
         * 当为false 转发到 http://localhost:8002/api/v1user/info
         */
        private Boolean stripPrefix = true;

        /**
         * Flag to indicate that this route should be retryable (if supported). Generally
         * retry requires a service ID and ribbon.
         * 是否支持重试如果支持的话  通常需要服务实例id 跟ribbon
         */
        private Boolean retryable;
    }
}