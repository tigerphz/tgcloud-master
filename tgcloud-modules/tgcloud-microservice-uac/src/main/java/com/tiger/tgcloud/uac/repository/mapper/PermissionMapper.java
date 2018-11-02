package com.tiger.tgcloud.uac.repository.mapper;

import com.tiger.tgcloud.core.mybatis.MyMapper;
import com.tiger.tgcloud.uac.model.domain.PermissionInfo;
import com.tiger.tgcloud.uac.model.query.PermissionParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/24 16:50
 * @version: V1.0
 * @modified by:
 */
@Mapper
@Component
public interface PermissionMapper extends MyMapper<PermissionInfo> {
    /**
     * 根据条件查询权限信息
     *
     * @param param
     * @return
     */
    List<PermissionInfo> selectByCondition(PermissionParam param);

    /**
     * 根据用户ID获取权限
     * @param userId
     * @return
     */
    List<PermissionInfo> selectByUserId(Long userId);

    /**
     * 根据用户名获取权限
     * @param userName
     * @return
     */
    List<PermissionInfo> selectByUserName(String userName);

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
