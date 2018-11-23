package com.tiger.tgcloud.uac.repository.mapper;

import com.tiger.tgcloud.core.mybatis.MyMapper;
import com.tiger.tgcloud.uac.api.model.domain.UserInfo;
import com.tiger.tgcloud.uac.model.query.UserParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/17 10:21
 * @version: V1.0
 * @modified by:
 */
@Mapper
@Component
public interface UserMapper extends MyMapper<UserInfo> {

    /**
     * 根据条件查询用户信息
     *
     * @param param
     * @return
     */
    List<UserInfo> selectByCondition(UserParam param);

    Boolean deleteUserRoleRelationByUserId(Long userId);

    Boolean insertUserRoleRelation(@Param("userId") Long userId, @Param("roleId") Long roleId);
}
