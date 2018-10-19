package com.tiger.tgcloud.security;

import com.tiger.tgcloud.model.UserInfo;
import com.tiger.tgcloud.security.core.SecurityUser;
import com.tiger.tgcloud.service.UserService;
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
    private UserService userService;

    /**
     * Load user by username user details.
     *
     * @param username the username
     * @return the user details
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("loadUserByUsername username={}", username);

        UserInfo user = userService.findByLoginName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        return new SecurityUser(user.getId(), user.getUserName(), user.getPassword(),
                user.getUserName(), user.getDeptid(), user.getDeptName(), user.getStatus(), null);
    }

}
