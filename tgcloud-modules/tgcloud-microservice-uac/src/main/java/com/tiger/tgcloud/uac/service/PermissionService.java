package com.tiger.tgcloud.uac.service;

import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.uac.model.bo.MenuBO;
import com.tiger.tgcloud.uac.model.bo.RouterTreeBO;
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
    PageInfo<MenuBO> selectByConditionWithPage(PermissionParam param);

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
    Boolean updatePermissionStatusById(PermissionInfo permissionInfo);

    /**
     * 删除部门信息
     *
     * @param id
     * @return
     */
    Boolean deletePermission(Long id);

    /**
     * 获取权限树列表
     *
     * @return
     */
    List<RouterTreeBO> selectRouterTree();

    /**
     * 获取用户的菜单
     *
     * @param userId
     * @return
     */
    List<RouterTreeBO> getRouterByUserId(long userId);

    /**
     * 获取角色拥有的权限
     *
     * @param roleId
     * @return
     */
    List<PermissionInfo> selectByRoleId(Long roleId);

    /**
     * 获取所有节点数据不包括按钮
     *
     * @return
     */
    List<PermissionInfo> selectMenuNode();
}
