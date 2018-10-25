package com.tiger.tgcloud.uac.service;

import com.tiger.tgcloud.uac.api.model.dto.UserRegisterDto;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/17 10:14
 * @version: V1.0
 * @modified by:
 */
public interface UserAuthService {
    /**
     * 注册用户.
     *
     * @param registerDto the register dto
     */
    void register(UserRegisterDto registerDto);

    /**
     * 激活用户.
     *
     * @param activeUserToken the active user token
     */
    void activeUser(String activeUserToken);

    /**
     * 检测邮箱是否可用
     *
     * @param email
     * @return
     */
    Boolean checkEmailActive(String email);
}
