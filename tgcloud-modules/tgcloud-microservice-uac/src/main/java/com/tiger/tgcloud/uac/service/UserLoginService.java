package com.tiger.tgcloud.uac.service;

import com.tiger.tgcloud.uac.model.bo.LoginedUserBO;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/29 16:26
 * @version: V1.0
 * @modified by:
 */
public interface UserLoginService {
    /**
     * 获取登陆信息
     *
     * @param userName
     * @return
     */
    LoginedUserBO getLoginedUserBO(String userName);
}
