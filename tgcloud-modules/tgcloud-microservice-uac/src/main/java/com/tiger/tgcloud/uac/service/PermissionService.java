package com.tiger.tgcloud.uac.service;

import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.uac.model.bo.MenuBO;
import com.tiger.tgcloud.uac.model.domain.PermissionInfo;
import com.tiger.tgcloud.uac.model.query.PermissionParam;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/24 16:25
 * @version: V1.0
 * @modified by:
 */
public interface PermissionService {
    /**
     * 分页查询权限列表
     *
     * @param param
     * @return
     */
    PageInfo<PermissionInfo> selectByConditionWithPage(PermissionParam param);

    /**
     * 添加部门信息
     *
     * @param permissionInfo
     * @return
     */
    Boolean addPermission(PermissionInfo permissionInfo);

    /**
     * 更新部门信息
     *
     * @param permissionInfo
     * @return
     */
    Boolean updatePermission(PermissionInfo permissionInfo);

    /**
     * 更新部门信息
     *
     * @param permissionInfo
     * @return
     */
    Boolean updateUserStatusById(PermissionInfo permissionInfo);

    /**
     * 获取权限树列表
     *
     * @param param
     * @return
     */
    List<MenuBO> selectPermTree(PermissionParam param);

    /**
     * 获取用户的菜单
     *
     * @param userId
     * @return
     */
    List<MenuBO> getMenuByUserId(long userId);

    /**
     * 获取角色拥有的权限
     *
     * @param roleId
     * @return
     */
    List<PermissionInfo> selectByRoleId(Long roleId);
}
