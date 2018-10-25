package com.tiger.tgcloud.uac.web.controller;

import com.tiger.tgcloud.core.support.BaseController;
import com.tiger.tgcloud.uac.service.DepartmentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 部门管理
 * @author: tiger
 * @date: 2018/10/24 16:19
 * @version: V1.0
 * @modified by:
 */
@RestController
@RequestMapping(value = "/department")
@Api(value = "Web - DepartmentController")
public class DepartmentController extends BaseController {
    @Autowired
    private DepartmentService departmentService;
}
