package com.tiger.tgcloud.monitor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/18 22:07
 * @version: V1.0
 * @modified by:
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {ResourceUtils.CLASSPATH_URL_PREFIX + "/static/", ResourceUtils.CLASSPATH_URL_PREFIX + "/public/",
            ResourceUtils.CLASSPATH_URL_PREFIX + "/resources/", ResourceUtils.CLASSPATH_URL_PREFIX + "/META-INF/resources/"};

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //注：ResourceUtils.CLASSPATH_URL_PREFIX就是"classpath:",如果不加这个，就会提示找不到资源
        registry.addResourceHandler("/webjars/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/META-INF/resources/webjars/");
        registry.addResourceHandler("/hystrix/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/hystrix/");
        registry.addResourceHandler("/").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }
}
