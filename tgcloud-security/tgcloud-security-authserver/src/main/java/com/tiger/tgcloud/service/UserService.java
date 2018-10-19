package com.tiger.tgcloud.service;

import com.tiger.tgcloud.core.support.IService;
import com.tiger.tgcloud.model.UserInfo;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/19 11:17
 * @version: V1.0
 * @modified by:
 */
public interface UserService extends IService<UserInfo> {

    /**
     * 根据登录名查询用户信息
     *
     * @param loginName the login name
     * @return the uac user
     */
    UserInfo findByLoginName(String loginName);

}
