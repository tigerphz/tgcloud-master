package com.tiger.tgcloud.security.core;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @description:
 * @author: tiger
 * @date: 2018/8/2 23:12
 * @version: V1.0
 * @modified by:
 */
public class SecurityUser implements UserDetails {
    private static final long serialVersionUID = 4872628781561412463L;

    private static final String ENABLE = "0";

    private Collection<GrantedAuthority> authorities;

    @Getter
    @Setter
    private Long userId;

    @Getter
    @Setter
    private String nickName;

    @Getter
    @Setter
    private String loginName;

    @Getter
    @Setter
    private String loginPwd;

    @Getter
    @Setter
    private Integer status;

    @Getter
    @Setter
    private Long groupId;

    @Getter
    @Setter
    private String groupName;

    public SecurityUser(Long userId, String loginName, String loginPwd, String nickName, Long groupId, String groupName) {
        this.setUserId(userId);
        this.setLoginName(loginName);
        this.setLoginPwd(loginPwd);
        this.setNickName(nickName);
        this.setGroupId(groupId);
        this.setGroupName(groupName);
    }

    public SecurityUser(Long userId, String loginName, String loginPwd, String nickName, Long groupId, String groupName, Integer status, Collection<GrantedAuthority> grantedAuthorities) {
        this.setUserId(userId);
        this.setLoginName(loginName);
        this.setLoginPwd(loginPwd);
        this.setNickName(nickName);
        this.setGroupId(groupId);
        this.setGroupName(groupName);
        this.setStatus(status);
        this.authorities = grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.getLoginPwd();
    }

    @Override
    public String getUsername() {
        return this.getLoginName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return StringUtils.equals(this.status.toString(), ENABLE);
    }

}
