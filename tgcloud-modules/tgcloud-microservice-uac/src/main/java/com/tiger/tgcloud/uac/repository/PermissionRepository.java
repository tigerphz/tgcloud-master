package com.tiger.tgcloud.uac.repository;

import com.tiger.tgcloud.core.support.BaseRepository;
import com.tiger.tgcloud.uac.model.domain.PermissionInfo;
import com.tiger.tgcloud.uac.model.query.PermissionParam;
import com.tiger.tgcloud.uac.repository.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/25 11:00
 * @version: V1.0
 * @modified by:
 */
@Repository
public class PermissionRepository extends BaseRepository<PermissionInfo> {
    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 根据条件查询用户信息
     *
     * @param param
     * @return
     */
    public List<PermissionInfo> selectByCondition(PermissionParam param) {
        return permissionMapper.selectByCondition(param);
    }

    /**
     * 根据用户ID获取权限
     *
     * @param userId
     * @return
     */
    public List<PermissionInfo> selectByUserId(Long userId) {
        return permissionMapper.selectByUserId(userId);
    }

    /**
     * 根据用户名获取权限
     *
     * @param userName
     * @return
     */
    public List<PermissionInfo> selectByUserName(String userName) {
        return permissionMapper.selectByUserName(userName);
    }

    /**
     * 获取角色拥有的权限
     *
     * @param roleId
     * @return
     */
    public List<PermissionInfo> selectByRoleId(Long roleId) {
        return permissionMapper.selectByRoleId(roleId);
    }

    /**
     * 获取所有节点数据不包括按钮
     *
     * @return
     */
    public List<PermissionInfo> selectMenuNode() {
        return permissionMapper.selectMenuNode();
    }
}
