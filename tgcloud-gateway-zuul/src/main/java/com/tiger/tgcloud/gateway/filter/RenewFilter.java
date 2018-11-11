package com.tiger.tgcloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.tiger.tgcloud.base.enums.ErrorCodeEnum;
import com.tiger.tgcloud.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/14 20:36
 * @version: V1.0
 * @modified by:
 */
@Component
@Slf4j
public class RenewFilter extends ZuulFilter {

    @Resource
    private JwtTokenStore jwtTokenStore;

    private static final String BEARER_TOKEN_TYPE = "Bearer ";
    private static final int EXPIRES_IN = 60 * 20;

    /**
     * Filter type string.
     *
     * @return the string
     */
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    /**
     * Filter order int.
     *
     * @return the int
     */
    @Override
    public int filterOrder() {
        return 10;
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
        log.info("RenewFilter - token续租...");
        RequestContext requestContext = RequestContext.getCurrentContext();
        try {
            doSomething(requestContext);
        } catch (Exception e) {
            log.error("RenewFilter - token续租. [FAIL] EXCEPTION={}", e.getMessage(), e);
            throw new BusinessException(ErrorCodeEnum.UAC10011041);
        }
        return null;
    }

    private void doSomething(RequestContext requestContext) {
        HttpServletRequest request = requestContext.getRequest();
        String token = StringUtils.substringAfter(request.getHeader(HttpHeaders.AUTHORIZATION), BEARER_TOKEN_TYPE);
        if (StringUtils.isEmpty(token)) {
            return;
        }
        OAuth2AccessToken oAuth2AccessToken = jwtTokenStore.readAccessToken(token);
        int expiresIn = oAuth2AccessToken.getExpiresIn();

        if (expiresIn < EXPIRES_IN) {
            HttpServletResponse servletResponse = requestContext.getResponse();
            servletResponse.addHeader("Renew-Header", "true");
        }
    }

}
