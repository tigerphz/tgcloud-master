package com.tiger.tgcloud.uac.web.controller;

import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.core.support.BaseController;
import com.tiger.tgcloud.uac.model.domain.PermissionInfo;
import com.tiger.tgcloud.uac.model.query.PermissionParam;
import com.tiger.tgcloud.uac.service.PermissionService;
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
 * @description: 权限管理
 * @author: tiger
 * @date: 2018/10/24 16:18
 * @version: V1.0
 * @modified by:
 */
@RestController
@RequestMapping(value = "/permissions")
@Api(value = "Web - PermissionController")
public class PermissionController extends BaseController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation("获取所有权限信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "param", dataType = "PermissionParam", value = "查询条件信息")
    })
    public Wrapper<PageInfo<PermissionInfo>> list(PermissionParam param) {
        PageInfo<PermissionInfo> permissionInfoPageInfos = permissionService.selectByConditionWithPage(param);

        return WrapMapper.ok(permissionInfoPageInfos);
    }
}
