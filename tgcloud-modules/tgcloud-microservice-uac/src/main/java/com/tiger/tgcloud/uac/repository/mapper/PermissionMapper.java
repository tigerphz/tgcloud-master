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
}
