package com.tiger.tgcloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.tiger.tgcloud.base.enums.ErrorCodeEnum;
import com.tiger.tgcloud.base.exception.BusinessException;
import com.tiger.tgcloud.core.interceptor.CoreHeaderInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/14 20:31
 * @version: V1.0
 * @modified by:
 */
@Slf4j
@Component
public class AuthHeaderFilter extends ZuulFilter {
    @Autowired
    private RouteLocator routeLocator;

    @Resource
    private JwtTokenStore jwtTokenStore;

    private static final String BEARER_TOKEN_TYPE = "Bearer ";
    private static final String OPTIONS = "OPTIONS";
    private static final String AUTH_PATH = "/auth";


    /**
     * Filter type string.
     *
     * @return the string
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * Filter order int.
     *
     * @return the int
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * Should filter boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * Run object.
     *
     * @return the object
     */
    @Override
    public Object run() {
        log.info("AuthHeaderFilter - 开始鉴权...");
        RequestContext requestContext = RequestContext.getCurrentContext();
        try {
            doSomething(requestContext);
        } catch (Exception e) {
            log.error("AuthHeaderFilter - [FAIL] EXCEPTION={}", e.getMessage(), e);
            throw new BusinessException(ErrorCodeEnum.UAC10011041);
        }
        return null;
    }

    private void doSomething(RequestContext requestContext) {
        HttpServletRequest request = requestContext.getRequest();
        String requestURI = request.getRequestURI();

        if (OPTIONS.equalsIgnoreCase(request.getMethod()) || requestURI.contains(AUTH_PATH)) {
            return;
        }

        List<Route> routeMaps = routeLocator.getRoutes();
        if (!routeMaps.stream().anyMatch(x ->
                PatternMatchUtils.simpleMatch(x.getPath(), requestURI)
        )) {
            return;
        }

        String token = StringUtils.substringAfter(request.getHeader(HttpHeaders.AUTHORIZATION), BEARER_TOKEN_TYPE);
        if (!StringUtils.isEmpty(token)) {
            String authHeader = BEARER_TOKEN_TYPE + token;
            // 传递给后续微服务
            requestContext.addZuulRequestHeader(CoreHeaderInterceptor.HEADER_LABEL, authHeader);
            log.info("authHeader={} ", authHeader);

            OAuth2Authentication authentication = jwtTokenStore.readAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication=" + authentication);
    }

}
