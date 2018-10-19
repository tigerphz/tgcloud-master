package com.tiger.tgcloud.service.impl;

import com.tiger.tgcloud.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: tiger
 * @date: 2018/8/6 13:19
 * @version: V1.0
 * @modified by:
 */
@Slf4j
@Component("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Override
    public boolean hasPermission(Authentication authentication, HttpServletRequest request) {
        log.info("调用:PermissionServiceImpl.permissionService");
        return true;
    }
}
