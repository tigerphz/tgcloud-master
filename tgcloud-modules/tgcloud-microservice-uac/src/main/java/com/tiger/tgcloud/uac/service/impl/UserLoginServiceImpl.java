package com.tiger.tgcloud.uac.service.impl;

import com.tiger.tgcloud.core.support.BaseService;
import com.tiger.tgcloud.uac.model.bo.LoginedUserBO;
import com.tiger.tgcloud.uac.model.bo.RouterTreeBO;
import com.tiger.tgcloud.uac.model.domain.PermissionInfo;
import com.tiger.tgcloud.uac.model.domain.RoleInfo;
import com.tiger.tgcloud.uac.api.model.domain.UserInfo;
import com.tiger.tgcloud.uac.repository.PermissionRepository;
import com.tiger.tgcloud.uac.repository.RoleRepository;
import com.tiger.tgcloud.uac.repository.UserRepository;
import com.tiger.tgcloud.uac.service.PermissionService;
import com.tiger.tgcloud.uac.service.UserLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/29 16:26
 * @version: V1.0
 * @modified by:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserLoginServiceImpl extends BaseService implements UserLoginService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionService permissionService;

    /**
     * 获取登陆信息
     *
     * @param userName
     * @return
     */
    @Override
    public LoginedUserBO getLoginedUserBO(String userName) {
        UserInfo where = new UserInfo();
        where.setUsername(userName);
        UserInfo userInfo = userRepository.selectOne(where);

        LoginedUserBO loginedUserBO = new LoginedUserBO();
        loginedUserBO.setId(userInfo.getId());
        loginedUserBO.setUsername(userInfo.getUsername());
        loginedUserBO.setNickname(userInfo.getNickname());

        List<RoleInfo> roleInfos = roleRepository.selectByUserName(userName);
        loginedUserBO.setRoles(roleInfos.stream().map(x -> x.getRolename()).collect(Collectors.toList()));

        List<PermissionInfo> permissionInfos = permissionRepository.selectByUserName(userName);
        loginedUserBO.setPermissions(permissionInfos.stream().filter(x -> StringUtils.isNotEmpty(x.getCode()))
                .map(x -> x.getCode().trim()).collect(Collectors.toList()));

        List<RouterTreeBO> routerTreeBOList = permissionService.getRouterByUserId(userInfo.getId());
        loginedUserBO.setRouters(routerTreeBOList);

        return loginedUserBO;
    }
}
