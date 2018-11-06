package com.tiger.tgcloud.uac.web.controller;

import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.base.dto.LoginAuthDto;
import com.tiger.tgcloud.core.support.BaseController;
import com.tiger.tgcloud.uac.mapping.PermissionMapping;
import com.tiger.tgcloud.uac.model.bo.MenuBO;
import com.tiger.tgcloud.uac.model.bo.RouterTreeBO;
import com.tiger.tgcloud.uac.model.domain.PermissionInfo;
import com.tiger.tgcloud.uac.model.query.PermissionParam;
import com.tiger.tgcloud.uac.model.vo.PermissionVO;
import com.tiger.tgcloud.uac.service.PermissionService;
import com.tiger.tgcloud.utils.wrapper.WrapMapper;
import com.tiger.tgcloud.utils.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 权限管理
 * @author: tiger
 * @date: 2018/10/24 16:18
 * @version: V1.0
 * @modified by:
 */
@RestController
@RequestMapping(value = "/perms")
@Api(value = "Web - PermissionController")
public class PermissionController extends BaseController {
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionMapping permissionMapping;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation("获取所有权限信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "param", dataType = "PermissionParam", value = "查询条件信息")
    })
    public Wrapper<PageInfo<MenuBO>> list(PermissionParam param) {
        PageInfo<MenuBO> permissionInfoPageInfos = permissionService.selectByConditionWithPage(param);

        return WrapMapper.ok(permissionInfoPageInfos);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation("添加权限信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "permission", dataType = "PermissionVO", value = "权限信息")
    })
    public Wrapper<Boolean> add(@Valid @RequestBody() PermissionVO permissionVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldError().getDefaultMessage();
            return WrapMapper.error(message);
        }

        PermissionInfo permissionInfo = permissionMapping.to(permissionVO);

        LoginAuthDto loginAuthDto = getLoginAuthDto();
        permissionInfo.setUpdateInfo(loginAuthDto);

        return WrapMapper.ok(permissionService.addPermission(permissionInfo));
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation("更新权限")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "permissionVO", dataType = "PermissionVO", value = "权限信息")
    })
    public Wrapper<Boolean> update(@Valid @RequestBody() PermissionVO permissionVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldError().getDefaultMessage();
            return WrapMapper.error(message);
        }

        PermissionInfo permissionInfo = permissionMapping.to(permissionVO);

        LoginAuthDto loginAuthDto = getLoginAuthDto();
        permissionInfo.setUpdateInfo(loginAuthDto);

        return WrapMapper.ok(permissionService.updatePermission(permissionInfo));
    }

    @RequestMapping(value = "/{id}/status/{status}", method = RequestMethod.PUT)
    @ApiOperation("更新权限状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "Long", value = "Id", required = true),
            @ApiImplicitParam(paramType = "path", name = "status", dataType = "Long", value = "状态", required = true)
    })
    public Wrapper<Boolean> updateStatus(@PathVariable(value = "id") Long id, @PathVariable(value = "status") Integer status) {
        PermissionInfo permissionInfo = new PermissionInfo();
        permissionInfo.setId(id);
        permissionInfo.setStatus(status);

        LoginAuthDto loginAuthDto = getLoginAuthDto();
        permissionInfo.setUpdateInfo(loginAuthDto);

        return WrapMapper.ok(permissionService.updatePermissionStatusById(permissionInfo));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation("删除权限")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "Long", value = "Id", required = true)
    })
    public Wrapper<Boolean> update(@PathVariable() Long id) {
        return WrapMapper.ok(permissionService.deletePermission(id));
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    @ApiOperation("获取菜单路由树")
    @ApiImplicitParams({
    })
    public Wrapper<List<RouterTreeBO>> tree() {
        List<RouterTreeBO> routerTreeBOList = permissionService.selectRouterTree();
        return WrapMapper.ok(routerTreeBOList);
    }

    @RequestMapping(value = "menunode", method = RequestMethod.GET)
    @ApiOperation("获取所有菜单节点")
    @ApiImplicitParams({
    })
    public Wrapper<List<PermissionInfo>> menuNode() {
        List<PermissionInfo> permissionInfoList = permissionService.selectMenuNode();
        return WrapMapper.ok(permissionInfoList);
    }
}
