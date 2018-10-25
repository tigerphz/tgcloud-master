package com.tiger.tgcloud.uac.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.core.support.BaseService;
import com.tiger.tgcloud.uac.model.domain.PermissionInfo;
import com.tiger.tgcloud.uac.model.query.PermissionParam;
import com.tiger.tgcloud.uac.repository.PermissionRepository;
import com.tiger.tgcloud.uac.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/24 16:48
 * @version: V1.0
 * @modified by:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl extends BaseService implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    /**
     * 分页查询用户列表
     *
     * @param param
     * @return
     */
    @Override
    public PageInfo<PermissionInfo> selectByConditionWithPage(PermissionParam param) {
        if (null != param.getPageNum() && null != param.getPageSize()) {
            PageHelper.startPage(param.getPageNum(), param.getPageSize());
        }

        List<PermissionInfo> permissionInfos = permissionRepository.selectByCondition(param);
        return new PageInfo<>(permissionInfos);
    }
}
