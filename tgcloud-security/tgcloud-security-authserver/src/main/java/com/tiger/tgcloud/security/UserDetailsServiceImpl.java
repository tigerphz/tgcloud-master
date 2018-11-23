package com.tiger.tgcloud.security;

import com.tiger.tgcloud.mapper.UserMapper;
import com.tiger.tgcloud.security.core.SecurityUser;
import com.tiger.tgcloud.uac.api.model.domain.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: tiger
 * @date: 2018/8/4 14:00
 * @version: V1.0
 * @modified by:
 */
@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    /**
     * Load user by username user details.
     *
     * @param username the username
     * @return the user details
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("loadUserByUsername username={}", username);

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);

        UserInfo user = userMapper.selectOne(userInfo);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        return new SecurityUser(user.getId(), user.getUsername(), user.getPasswordhash(),
                user.getNickname(), user.getDeptid(), user.getDeptname(), user.getStatus(), null);
    }

}
