package com.tiger.tgcloud.service.impl;

import com.tiger.tgcloud.core.support.BaseRepository;
import com.tiger.tgcloud.mapper.UserMapper;
import com.tiger.tgcloud.model.UserInfo;
import com.tiger.tgcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/19 11:18
 * @version: V1.0
 * @modified by:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseRepository<UserInfo> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public UserInfo findByLoginName(String userName) {
        logger.info("findByLoginName - 根据用户名查询用户信息. userName={}", userName);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);

        return userMapper.selectOne(userInfo);
    }
    
}
