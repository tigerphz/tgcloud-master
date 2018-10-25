package com.tiger.tgcloud.uac.web.controller;

import com.tiger.tgcloud.core.support.BaseController;
import com.tiger.tgcloud.uac.service.PermissionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 权限管理
 * @author: tiger
 * @date: 2018/10/24 16:18
 * @version: V1.0
 * @modified by:
 */
@RestController
@RequestMapping(value = "/permission")
@Api(value = "Web - PermissionController")
public class PermissionController extends BaseController {
    @Autowired
    private PermissionService permissionService;
}
