package com.tiger.tgcloud.core.interceptor;

import com.tiger.tgcloud.base.constant.GlobalConstant;
import com.tiger.tgcloud.base.dto.LoginAuthDto;
import com.tiger.tgcloud.core.annotation.NoNeedAccessAuthentication;
import com.tiger.tgcloud.utils.ThreadLocalMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: tiger
 * @date: 2018/8/3 20:30
 * @version: V1.0
 * @modified by:
 */
public abstract class TokenInterceptor implements HandlerInterceptor {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * OPTIONS方法是用于请求获得由Request-URI标识的资源在请求/响应的通信过程中可以使用的功能选项。
     * 通过这个方法，客户端可以在采取具体资源请求之前，决定对该资源采取何种必要措施，或者了解服务器的性能。
     */
    protected static final String OPTIONS = "OPTIONS";
    protected static final String AUTH_PATH1 = "/auth";
    protected static final String AUTH_PATH2 = "/oauth";
    protected static final String AUTH_PATH3 = "/error";
    protected static final String AUTH_PATH4 = "/api";

    /**
     * After completion.
     *
     * @param request  the request
     * @param response the response
     * @param arg2     the arg 2
     * @param ex       the ex
     * @throws Exception the exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception ex) throws Exception {
        if (ex != null) {
            logger.error("<== afterCompletion - 解析token失败. ex={}", ex.getMessage(), ex);
            this.handleException(response);
        }
    }

    /**
     * Post handle.
     *
     * @param request  the request
     * @param response the response
     * @param arg2     the arg 2
     * @param mv       the mv
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView mv) {
    }

    /**
     * Pre handle boolean.
     *
     * @param request  the request
     * @param response the response
     * @param handler  the handler
     * @return the boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String uri = request.getRequestURI();
        logger.info("<== preHandle - 权限拦截器.  url={}", uri);
        if (uri.contains(AUTH_PATH1) || uri.contains(AUTH_PATH2) || uri.contains(AUTH_PATH3) || uri.contains(AUTH_PATH4)) {
            logger.info("<== preHandle - 配置URL不走认证.  url={}", uri);
            return true;
        }

        if (OPTIONS.equalsIgnoreCase(request.getMethod())) {
            logger.info("<== preHandle - OPTIONS请求模式不走认证.  url={}", uri);
            return true;
        }

        if (isHaveAccess(handler)) {
            logger.info("<== preHandle - 不需要认证注解不走认证.  token={}");
            return true;
        }

        String token = StringUtils.substringAfter(request.getHeader(HttpHeaders.AUTHORIZATION), "Bearer ");
        logger.info("<== preHandle - 权限拦截器.  token={}", token);
        LoginAuthDto loginUser = getLoginAuthDto(token);
        if (loginUser == null) {
            logger.error("获取用户信息失败, 不允许操作");
            return false;
        }
        logger.info("<== preHandle - 权限拦截器.  loginUser={}", loginUser);
        ThreadLocalMap.put(GlobalConstant.Sys.TOKEN_AUTH_DTO, loginUser);
        logger.info("<== preHandle - 权限拦截器.  url={}, loginUser={}", uri, loginUser);
        return true;
    }

    private void handleException(HttpServletResponse res) throws IOException {
        res.resetBuffer();
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write("{\"code\":100009 ,\"message\" :\"解析token失败\"}");
        res.flushBuffer();
    }

    protected boolean isHaveAccess(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Method method = handlerMethod.getMethod();

        NoNeedAccessAuthentication responseBody = AnnotationUtils.findAnnotation(method, NoNeedAccessAuthentication.class);
        return responseBody != null;
    }

    /**
     * 通过token获取登陆信息
     *
     * @param token
     * @return
     */
    protected abstract LoginAuthDto getLoginAuthDto(String token);
}
