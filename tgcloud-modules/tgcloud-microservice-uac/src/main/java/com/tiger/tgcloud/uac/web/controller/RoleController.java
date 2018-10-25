package com.tiger.tgcloud.uac.web.controller;

import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.base.dto.LoginAuthDto;
import com.tiger.tgcloud.core.support.BaseController;
import com.tiger.tgcloud.uac.mapping.RoleMapping;
import com.tiger.tgcloud.uac.model.domain.RoleInfo;
import com.tiger.tgcloud.uac.model.query.RoleParam;
import com.tiger.tgcloud.uac.model.vo.RoleVO;
import com.tiger.tgcloud.uac.service.RoleService;
import com.tiger.tgcloud.utils.wrapper.WrapMapper;
import com.tiger.tgcloud.utils.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

/**
 * @description: 角色管理
 * @author: tiger
 * @date: 2018/10/24 16:18
 * @version: V1.0
 * @modified by:
 */
@RestController
@RequestMapping(value = "/roles")
@Api(value = "Web - RoleController")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMapping roleMapping;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation("获取所有权角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "param", dataType = "RoleParam", value = "获取所有权角色信息")
    })
    public Wrapper<PageInfo<RoleInfo>> list(RoleParam param) {
        PageInfo<RoleInfo> roleInfoPageInfo = roleService.selectByConditionWithPage(param);

        return WrapMapper.ok(roleInfoPageInfo);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ApiOperation("添加角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "role", dataType = "RoleVO", value = "角色信息")
    })
    public Wrapper<Boolean> add(@Valid @RequestBody() RoleVO roleVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldError().getDefaultMessage();
            return WrapMapper.error(message);
        }

        RoleInfo roleInfo = roleMapping.to(roleVO);
        LoginAuthDto loginAuthDto = getLoginAuthDto();

        roleInfo.setUpdateInfo(loginAuthDto);

        return WrapMapper.ok(roleService.addRole(roleInfo));
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ApiOperation("更新角色")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "roleVO", dataType = "RoleVO", value = "角色信息")
    })
    public Wrapper<Boolean> update(@Valid @RequestBody() RoleVO roleVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldError().getDefaultMessage();
            return WrapMapper.error(message);
        }

        RoleInfo roleInfo = roleMapping.to(roleVO);

        LoginAuthDto loginAuthDto = getLoginAuthDto();
        roleInfo.setUpdateInfo(loginAuthDto);

        return WrapMapper.ok(roleService.updateRole(roleInfo));
    }

    @RequestMapping(value = "/{id}/status/{status}", method = RequestMethod.PUT)
    @ApiOperation(value = "更新角色状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "Long", value = "Id", required = true),
            @ApiImplicitParam(paramType = "path", name = "status", dataType = "Long", value = "状态", required = true)
    })
    public Wrapper<Boolean> updateStatus(@PathParam(value = "id") Long id, @PathParam(value = "status") Integer status) {
        RoleInfo roleInfo = new RoleInfo();
        roleInfo.setId(id);
        roleInfo.setStatus(status);

        LoginAuthDto loginAuthDto = getLoginAuthDto();
        roleInfo.setUpdateInfo(loginAuthDto);

        return WrapMapper.ok(roleService.updateUserStatusById(roleInfo));
    }
}
