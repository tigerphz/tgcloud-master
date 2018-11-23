package com.tiger.tgcloud.uac.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.tiger.tgcloud.base.enums.ErrorCodeEnum;
import com.tiger.tgcloud.core.support.BaseService;
import com.tiger.tgcloud.uac.api.exceptions.UacBizException;
import com.tiger.tgcloud.uac.api.model.domain.UserInfo;
import com.tiger.tgcloud.uac.model.enums.UserSourceEnum;
import com.tiger.tgcloud.uac.model.enums.UserTypeEnum;
import com.tiger.tgcloud.uac.model.query.UserParam;
import com.tiger.tgcloud.uac.repository.UserRepository;
import com.tiger.tgcloud.uac.service.UserService;
import com.tiger.tgcloud.uac.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/24 16:46
 * @version: V1.0
 * @modified by:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseService implements UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * 分页查询用户列表
     *
     * @param param
     * @return
     */
    @Override
    public PageInfo<UserInfo> selectByConditionWithPage(UserParam param) {
        if (null != param.getPageNum() && null != param.getPageSize()) {
            PageHelper.startPage(param.getPageNum(), param.getPageSize());
        }

        List<UserInfo> userInfos = userRepository.selectByCondition(param);
        return new PageInfo<>(userInfos);
    }

    /**
     * 根据ID查询用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public UserInfo getUserById(Long userId) {
        return userRepository.selectByKey(userId);
    }

    /**
     * 更新用户状态
     *
     * @param userInfo
     * @return
     */
    @Override
    public Boolean updateUserStatusById(UserInfo userInfo) {
        CheckUpdateUserInfo(userInfo);
        return userRepository.updateByPrimaryKeySelective(userInfo);
    }

    /**
     * 更新用户状态
     *
     * @param userInfo
     * @return
     */
    @Override
    public Boolean updateUserById(UserInfo userInfo) {
        CheckUpdateUserInfo(userInfo);
        return userRepository.updateByPrimaryKeySelective(userInfo);
    }

    private void CheckUpdateUserInfo(UserInfo userInfo) {
        long userId = userInfo.getId();
        UserInfo param = new UserInfo();
        param.setId(userId);
        int count = userRepository.selectCount(param);
        if (count == 0) {
            throw new UacBizException(ErrorCodeEnum.UAC10011003, userId);
        }
    }

    /**
     * 绑定用户角色关系
     *
     * @param userId
     * @param roleIds
     * @return
     */
    @Override
    public Boolean bindUserRoleRelation(Long userId, List<Long> roleIds) {
        userRepository.deleteUserRoleRelationByUserId(userId);
        for (Long roleId : roleIds) {
            userRepository.insertUserRoleRelation(userId, roleId);
        }

        return true;
    }

    /**
     * 保存用户
     *
     * @param userInfo
     * @return
     */
    @Override
    public Boolean addUser(UserInfo userInfo) {
        String password = userInfo.getPasswordhash();
        Preconditions.checkArgument(!StringUtils.isEmpty(password), "密码不能为空");

        UserInfo param = new UserInfo();
        param.setUsername(userInfo.getUsername());
        int count = userRepository.selectCount(param);
        if (count > 0) {
            throw new UacBizException(ErrorCodeEnum.UAC10011025, userInfo.getUsername());
        }

        String loginPwd = userInfo.getPasswordhash();
        String md5pwd = Md5Util.encrypt(loginPwd);

        userInfo.setId(generateId());
        userInfo.setPasswordhash(md5pwd);

        userInfo.setType(UserTypeEnum.OPERATE.getKey());
        userInfo.setSource(UserSourceEnum.INSERT.getKey());

        return userRepository.save(userInfo);
    }
}
