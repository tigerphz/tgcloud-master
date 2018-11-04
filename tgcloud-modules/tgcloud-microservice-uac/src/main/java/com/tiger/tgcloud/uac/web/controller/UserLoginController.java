package com.tiger.tgcloud.uac.web.controller;

import com.tiger.tgcloud.base.dto.LoginAuthDto;
import com.tiger.tgcloud.core.support.BaseController;
import com.tiger.tgcloud.uac.model.bo.LoginedUserBO;
import com.tiger.tgcloud.uac.service.UserLoginService;
import com.tiger.tgcloud.utils.wrapper.WrapMapper;
import com.tiger.tgcloud.utils.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    private UserLoginService userLoginService;

    @RequestMapping(value = "/logininfo", method = RequestMethod.GET)
    @ApiOperation("获取登录用户角色权限信息")
    public Wrapper<LoginedUserBO> getLoginedUserRolesPerms() {
        LoginAuthDto loginAuthDto = getLoginAuthDto();

        LoginedUserBO loginedUserBO = userLoginService.getLoginedUserBO(loginAuthDto.getUserName());

        return WrapMapper.ok(loginedUserBO);
    }
}
