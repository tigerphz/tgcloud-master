package com.tiger.tgcloud.uac.repository;

import com.tiger.tgcloud.core.support.BaseRepository;
import com.tiger.tgcloud.uac.model.domain.PermissionInfo;
import com.tiger.tgcloud.uac.model.domain.RoleInfo;
import com.tiger.tgcloud.uac.model.query.RoleParam;
import com.tiger.tgcloud.uac.repository.mapper.RoleMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/25 10:59
 * @version: V1.0
 * @modified by:
 */
@Repository
public class RoleRepository extends BaseRepository<RoleInfo> {
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据条件查询用户信息
     *
     * @param param
     * @return
     */
    public List<RoleInfo> selectByCondition(RoleParam param) {
        return roleMapper.selectByCondition(param);
    }

    public List<RoleInfo> selectByUserName(String userName) {
        return roleMapper.selectByUserName(userName);
    }

    public List<RoleInfo> selectByUserId(Long userId) {
        return roleMapper.selectByUserId(userId);
    }

    public Boolean deleteRolePermRelationByRoleId(Long roleId) {
        return roleMapper.deleteRolePermRelationByRoleId(roleId);
    }

    public Boolean insertRolePermRelation(Long roleId, Long permId) {
        return roleMapper.insertRolePermRelation(roleId, permId);
    }
}
