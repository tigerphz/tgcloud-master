package com.tiger.tgcloud.uac.service;

import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.uac.model.domain.RoleInfo;
import com.tiger.tgcloud.uac.model.query.RoleParam;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/24 16:21
 * @version: V1.0
 * @modified by:
 */
public interface RoleService {
    /**
     * 分页查询用户列表
     *
     * @param param
     * @return
     */
    PageInfo<RoleInfo> selectByConditionWithPage(RoleParam param);

    /**
     * 添加角色
     *
     * @param roleInfo
     * @return
     */
    Boolean addRole(RoleInfo roleInfo);

    /**
     * 更新角色
     *
     * @param roleInfo
     * @return
     */
    Boolean updateRole(RoleInfo roleInfo);

    /**
     * 更新角色状态
     *
     * @return
     */
    Boolean updateRoleStatusById(RoleInfo roleInfo);

    List<RoleInfo> selectByUserId(Long userId);

    Boolean bindRolePermRelation(Long roleId, List<Long> permIds);
}
