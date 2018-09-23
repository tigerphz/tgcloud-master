package com.tiger.tgcloud.security;

import com.google.common.collect.Lists;
import com.tiger.tgcloud.security.core.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

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
    private PasswordEncoder passwordEncoder;

    /**
     * Load user by username user details.
     *
     * @param username the username
     * @return the user details
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("loadUserByUsername username={}", username);

        List<GrantedAuthority> grantedAuthorityList = Lists.newArrayList();
        GrantedAuthority authority = new SimpleGrantedAuthority("view");
        grantedAuthorityList.add(authority);

        String password = "admin";
        String hashedPassword = passwordEncoder.encode(password);

        return new SecurityUser(1L, "admin", hashedPassword,
                "", 0L, "", "ENABLE", null);
    }
}
