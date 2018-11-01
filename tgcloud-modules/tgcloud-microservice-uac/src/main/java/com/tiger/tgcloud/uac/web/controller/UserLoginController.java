package com.tiger.tgcloud.uac.web.controller;

import com.tiger.tgcloud.base.dto.LoginAuthDto;
import com.tiger.tgcloud.core.support.BaseController;
import com.tiger.tgcloud.uac.model.bo.LoginedUserBO;
import com.tiger.tgcloud.uac.model.bo.MenuBO;
import com.tiger.tgcloud.uac.model.domain.PermissionInfo;
import com.tiger.tgcloud.uac.service.PermissionService;
import com.tiger.tgcloud.uac.service.UserLoginService;
import com.tiger.tgcloud.utils.wrapper.WrapMapper;
import com.tiger.tgcloud.utils.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @description: 登陆用户信息
 * @author: tiger
 * @date: 2018/10/26 17:40
 * @version: V1.0
 * @modified by:
 */
@RestController
@RequestMapping(value = "/users")
@Api(value = "Web - UserLoginController")
@Slf4j
public class UserLoginController extends BaseController {
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserLoginService userLoginService;

    @RequestMapping(value = "/logininfo", method = RequestMethod.GET)
    @ApiOperation("获取登录用户角色权限信息")
    public Wrapper<LoginedUserBO> getLoginedUserRolesPerms() {
        LoginAuthDto loginAuthDto = getLoginAuthDto();

        LoginedUserBO loginedUserBO = userLoginService.getLoginedUserBO(loginAuthDto.getUserName());

        return WrapMapper.ok(loginedUserBO);
    }

    @RequestMapping(value = "/{id}/menus", method = RequestMethod.GET)
    @ApiOperation("获取用户菜单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "Long", value = "用户Id")
    })
    public Wrapper<List<MenuBO>> getMenus(@PathParam("id") Long userId) {
        List<MenuBO> menuBOList = permissionService.getMenuByUserId(userId);

        return WrapMapper.ok(menuBOList);
    }

    @RequestMapping(value = "/roles/{id}/perms", method = RequestMethod.GET)
    @ApiOperation("获取角色拥有的权限")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "Long", value = "角色Id", required = true),
    })
    public Wrapper<List<PermissionInfo>> getUserPerms(@PathVariable("id") Long roleId) {
        List<PermissionInfo> roleInfos = permissionService.selectByRoleId(roleId);

        return WrapMapper.ok(roleInfos);
    }
}
