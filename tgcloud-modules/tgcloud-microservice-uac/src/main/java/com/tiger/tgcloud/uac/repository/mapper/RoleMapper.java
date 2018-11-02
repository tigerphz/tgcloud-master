package com.tiger.tgcloud.uac.repository.mapper;

import com.tiger.tgcloud.core.mybatis.MyMapper;
import com.tiger.tgcloud.uac.model.domain.PermissionInfo;
import com.tiger.tgcloud.uac.model.domain.RoleInfo;
import com.tiger.tgcloud.uac.model.query.RoleParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
public interface RoleMapper extends MyMapper<RoleInfo> {
    /**
     * 根据条件查询用户信息
     *
     * @param param
     * @return
     */
    List<RoleInfo> selectByCondition(RoleParam param);

    List<RoleInfo> selectByUserName(String userName);

    List<RoleInfo> selectByUserId(Long userId);

    Boolean deleteRolePermRelationByRoleId(Long roleId);

    Boolean insertRolePermRelation(@Param("roleId") Long roleId, @Param("permId") Long permId);
}
