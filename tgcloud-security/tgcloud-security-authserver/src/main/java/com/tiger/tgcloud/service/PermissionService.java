package com.tiger.tgcloud.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: tiger
 * @date: 2018/8/6 13:20
 * @version: V1.0
 * @modified by:
 */
public interface PermissionService {

    /**
     * Has permission boolean.
     *
     * @param authentication the authentication
     * @param request        the request
     * @return the boolean
     */
    boolean hasPermission(Authentication authentication, HttpServletRequest request);

}
