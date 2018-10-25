package com.tiger.tgcloud.uac.web.controller;

import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.core.support.BaseController;
import com.tiger.tgcloud.uac.model.domain.DepartmentInfo;
import com.tiger.tgcloud.uac.model.query.DepartmentParam;
import com.tiger.tgcloud.uac.service.DepartmentService;
import com.tiger.tgcloud.utils.wrapper.WrapMapper;
import com.tiger.tgcloud.utils.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 部门管理
 * @author: tiger
 * @date: 2018/10/24 16:19
 * @version: V1.0
 * @modified by:
 */
@RestController
@RequestMapping(value = "/departments")
@Api(value = "Web - DepartmentController")
public class DepartmentController extends BaseController {
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation("获取所有权限信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "param", dataType = "DepartmentParam", value = "获取所有权限信息")
    })
    public Wrapper<PageInfo<DepartmentInfo>> list(DepartmentParam param) {
        PageInfo<DepartmentInfo> permissionInfoPageInfos = departmentService.selectByConditionWithPage(param);

        return WrapMapper.ok(permissionInfoPageInfos);
    }
}
