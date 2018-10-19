package com.tiger.tgcloud.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiger.tgcloud.utils.wrapper.WrapMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: tiger
 * @date: 2018/8/2 22:16
 * @version: V1.0
 * @modified by:
 */
@Component("AuthenticationFailureHandlerConfig")
public class PcAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Resource
    private ObjectMapper objectMapper;

    /**
     * On authentication failure.
     *
     * @param request   the request
     * @param response  the response
     * @param exception the exception
     * @throws IOException      the io exception
     *                              UsernameNotFoundException 用户找不到
     *                              BadCredentialsException 坏的凭据
     *                              AccountStatusException用户状态异常它包含如下子类
     *                              AccountExpiredException 账户过期
     *                              LockedException 账户锁定
     *                              DisabledException账户不可用
     *                              CredentialsExpiredException证书过期
     * @throws ServletException the servlet exception
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        logger.info("登录失败");

        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=UTF-8");

        String errorMessage;

        try {
            throw exception;
        } catch (BadCredentialsException ex) {
            errorMessage = "用户名或密码错误";
        } catch (DisabledException ex) {
            errorMessage = "账号不可用";
        } catch (CredentialsExpiredException ex) {
            errorMessage = "证书过期";
        } catch (AuthenticationException ex) {
            errorMessage = exception.getMessage();
        }

        response.getWriter().write(objectMapper.writeValueAsString(WrapMapper.error(errorMessage)));
    }
}
