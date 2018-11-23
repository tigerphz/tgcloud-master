package com.tiger.tgcloud.uac.web.controller;

import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.base.dto.LoginAuthDto;
import com.tiger.tgcloud.core.support.BaseController;
import com.tiger.tgcloud.uac.mapping.UserMapping;
import com.tiger.tgcloud.uac.model.domain.RoleInfo;
import com.tiger.tgcloud.uac.api.model.domain.UserInfo;
import com.tiger.tgcloud.uac.model.enums.UserTypeEnum;
import com.tiger.tgcloud.uac.model.query.UserParam;
import com.tiger.tgcloud.uac.model.vo.UserRolesVO;
import com.tiger.tgcloud.uac.model.vo.UserVO;
import com.tiger.tgcloud.uac.service.RoleService;
import com.tiger.tgcloud.uac.service.UserService;
import com.tiger.tgcloud.utils.wrapper.WrapMapper;
import com.tiger.tgcloud.utils.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @description: 用户管理
 * @author: tiger
 * @date: 2018/10/24 16:16
 * @version: V1.0
 * @modified by:
 */
@RestController
@RequestMapping(value = "/users")
@Api(value = "Web - UserController")
@Slf4j
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserMapping userMapping;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation("获取所有用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "param", dataType = "UserParam", value = "查询条件")
    })
    public Wrapper<PageInfo<UserVO>> list(UserParam param) {
        param.setType(UserTypeEnum.OPERATE.getKey());
        PageInfo<UserInfo> userInfoPageInfo = userService.selectByConditionWithPage(param);

        List<UserVO> userList = userMapping.from(userInfoPageInfo.getList());
        PageInfo<UserVO> userVOPageInfo = new PageInfo<>(userList);

        return WrapMapper.ok(userVOPageInfo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation("通过userId获取用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "Long", value = "id", required = true),
    })
    public Wrapper<UserVO> getUserById(@PathParam(value = "id") Long userId) {
        UserInfo userInfo = userService.getUserById(userId);
        UserVO userVO = userMapping.from(userInfo);

        return WrapMapper.ok(userVO);
    }

    @RequestMapping(value = "/{id}/status/{status}", method = RequestMethod.PUT)
    @ApiOperation("更新用户状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "Long", value = "用户ID", required = true),
            @ApiImplicitParam(paramType = "path", name = "status", dataType = "Long", value = "状态", required = true)
    })
    public Wrapper<Boolean> updateStatus(@PathVariable(value = "id") Long userId, @PathVariable(value = "status") Integer status) {
        LoginAuthDto loginAuthDto = getLoginAuthDto();

        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        userInfo.setStatus(status);
        userInfo.setUpdateInfo(loginAuthDto);

        return WrapMapper.ok(userService.updateUserStatusById(userInfo));
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation("更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "userVO", dataType = "UserVO", value = "用户信息")
    })
    public Wrapper<Boolean> update(@Valid @RequestBody() UserVO userVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldError().getDefaultMessage();
            return WrapMapper.error(message);
        }

        UserInfo userInfo = userMapping.to(userVO);
        //不修改用户名
        userInfo.setUsername(null);
        userInfo.setPasswordhash(null);

        LoginAuthDto loginAuthDto = getLoginAuthDto();
        userInfo.setUpdateInfo(loginAuthDto);

        return WrapMapper.ok(userService.updateUserById(userInfo));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation("添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "user", dataType = "UserVO", value = "用户信息")
    })
    public Wrapper<Boolean> add(@Valid @RequestBody() UserVO userVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldError().getDefaultMessage();
            return WrapMapper.error(message);
        }

        UserInfo userInfo = userMapping.to(userVO);

        LoginAuthDto loginAuthDto = getLoginAuthDto();
        userInfo.setUpdateInfo(loginAuthDto);

        return WrapMapper.ok(userService.addUser(userInfo));
    }

    @RequestMapping(value = "{id}/roles", method = RequestMethod.GET)
    @ApiOperation("获取用户拥有的角色")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "Long", value = "用户Id", required = true),
    })
    public Wrapper<List<RoleInfo>> getUserRoles(@PathVariable(name = "id") Long userId) {
        List<RoleInfo> roleInfos = roleService.selectByUserId(userId);
        return WrapMapper.ok(roleInfos);
    }

    @RequestMapping(value = "/bindRoles", method = RequestMethod.POST)
    @ApiOperation(value = "绑定用户角色关系")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "userRolesVO", dataType = "UserRolesVO", value = "用户Id与角色Id关系信息", required = true),
    })
    public Wrapper<Boolean> bindUserRoles(@Valid @RequestBody UserRolesVO userRolesVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldError().getDefaultMessage();
            return WrapMapper.error(message);
        }
        boolean isSucc = userService.bindUserRoleRelation(userRolesVO.getUserId(), userRolesVO.getRoleIds());
        return WrapMapper.ok(isSucc);
    }

}
