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
}
