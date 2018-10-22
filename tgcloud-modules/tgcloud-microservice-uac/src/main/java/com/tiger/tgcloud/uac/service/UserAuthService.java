package com.tiger.tgcloud.uac.service;

import com.tiger.tgcloud.core.support.IService;
import com.tiger.tgcloud.uac.api.model.dto.UserRegisterDto;
import com.tiger.tgcloud.uac.model.domain.UserInfo;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/17 10:14
 * @version: V1.0
 * @modified by:
 */
public interface UserAuthService extends IService<UserInfo> {
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
}
