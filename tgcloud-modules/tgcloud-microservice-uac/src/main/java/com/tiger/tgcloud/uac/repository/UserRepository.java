package com.tiger.tgcloud.uac.repository;

import com.tiger.tgcloud.core.support.BaseRepository;
import com.tiger.tgcloud.uac.api.model.domain.UserInfo;
import com.tiger.tgcloud.uac.model.query.UserParam;
import com.tiger.tgcloud.uac.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/25 10:52
 * @version: V1.0
 * @modified by:
 */
@Repository
public class UserRepository extends BaseRepository<UserInfo> {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据条件查询用户信息
     *
     * @param param
     * @return
     */
    public List<UserInfo> selectByCondition(UserParam param) {
        return userMapper.selectByCondition(param);
    }

    public Boolean deleteUserRoleRelationByUserId(Long userId) {
        return userMapper.deleteUserRoleRelationByUserId(userId);
    }

    public Boolean insertUserRoleRelation(Long userId, Long roleId) {
        return userMapper.insertUserRoleRelation(userId, roleId);
    }
}
