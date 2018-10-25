package com.tiger.tgcloud.uac.service.impl;

import com.tiger.tgcloud.core.support.BaseService;
import com.tiger.tgcloud.uac.repository.PermissionRepository;
import com.tiger.tgcloud.uac.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
