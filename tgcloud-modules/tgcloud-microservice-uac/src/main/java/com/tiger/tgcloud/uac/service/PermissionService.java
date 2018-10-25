package com.tiger.tgcloud.uac.service;

import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.uac.model.domain.PermissionInfo;
import com.tiger.tgcloud.uac.model.query.PermissionParam;

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
}
