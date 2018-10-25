package com.tiger.tgcloud.uac.web.controller;

import com.tiger.tgcloud.core.support.BaseController;
import com.tiger.tgcloud.uac.api.model.dto.UserRegisterDto;
import com.tiger.tgcloud.uac.service.UserAuthService;
import com.tiger.tgcloud.utils.wrapper.WrapMapper;
import com.tiger.tgcloud.utils.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @description: 用户登陆注册等接口, 无需权限认证
 * @author: tiger
 * @date: 2018/10/17 10:10
 * @version: V1.0
 * @modified by:
 */
@RestController
@RequestMapping(value = "/auth")
@Api(value = "Web-UserAuthController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserAuthController extends BaseController {

    @Autowired
    private UserAuthService userAuthService;

    /**
     * 注册用户.
     *
     * @param user the user
     * @return the wrapper
     */
    @PostMapping(value = "/register")
    @ApiOperation(httpMethod = "POST", value = "注册用户")
    public Wrapper registerUser(@Valid UserRegisterDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldError().getDefaultMessage();
            return WrapMapper.error(message);
        }

        userAuthService.register(user);
        return WrapMapper.ok();
    }

    /**
     * 校验邮箱.
     *
     * @param email the email
     * @return the wrapper
     */
    @PostMapping(value = "/checkEmailActive/{email:.+}")
    @ApiOperation(httpMethod = "POST", value = "校验邮箱")
    public Wrapper<Boolean> checkEmailActive(@PathVariable("email") String email) {
        return WrapMapper.ok(userAuthService.checkEmailActive(email));
    }

    /**
     * 激活用户.
     *
     * @param activeUserToken the active user token
     * @return the wrapper
     */
    @GetMapping(value = "/activeUser/{activeUserToken}")
    @ApiOperation(httpMethod = "POST", value = "激活用户")
    public Wrapper activeUser(@PathVariable String activeUserToken) {
        userAuthService.activeUser(activeUserToken);
        return WrapMapper.ok("激活成功");
    }
}
