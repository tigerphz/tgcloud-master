package com.tiger.tgcloud.uac.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.base.enums.ErrorCodeEnum;
import com.tiger.tgcloud.core.support.BaseService;
import com.tiger.tgcloud.uac.api.exceptions.UacBizException;
import com.tiger.tgcloud.uac.model.domain.PermissionInfo;
import com.tiger.tgcloud.uac.model.domain.RoleInfo;
import com.tiger.tgcloud.uac.model.query.RoleParam;
import com.tiger.tgcloud.uac.repository.RoleRepository;
import com.tiger.tgcloud.uac.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/24 16:47
 * @version: V1.0
 * @modified by:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends BaseService implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    /**
     * 分页查询用户列表
     *
     * @param param
     * @return
     */
    @Override
    public PageInfo<RoleInfo> selectByConditionWithPage(RoleParam param) {
        if (null != param.getPageNum() && null != param.getPageSize()) {
            PageHelper.startPage(param.getPageNum(), param.getPageSize());
        }

        List<RoleInfo> roleInfos = roleRepository.selectByCondition(param);
        return new PageInfo<>(roleInfos);
    }

    /**
     * 添加角色
     *
     * @param roleInfo
     * @return
     */
    @Override
    public Boolean addRole(RoleInfo roleInfo) {
        RoleInfo param = new RoleInfo();
        param.setRolename(roleInfo.getRolename());
        if (roleRepository.selectCount(param) > 0) {
            throw new UacBizException(ErrorCodeEnum.UAC10012009);
        }

        roleInfo.setId(generateId());

        return roleRepository.save(roleInfo);
    }

    /**
     * 更新角色
     *
     * @param roleInfo
     * @return
     */
    @Override
    public Boolean updateRole(RoleInfo roleInfo) {
        CheckUpdateRole(roleInfo);
        return roleRepository.updateByPrimaryKeySelective(roleInfo);
    }

    /**
     * 更新角色状态
     *
     * @param roleInfo
     * @return
     */
    @Override
    public Boolean updateRoleStatusById(RoleInfo roleInfo) {
        CheckUpdateRole(roleInfo);
        return roleRepository.updateByPrimaryKeySelective(roleInfo);
    }

    @Override
    public List<RoleInfo> selectByUserId(Long userId) {
        return roleRepository.selectByUserId(userId);
    }

    private void CheckUpdateRole(RoleInfo roleInfo) {
        long roleId = roleInfo.getId();
        RoleInfo param = new RoleInfo();
        param.setId(roleId);
        int count = roleRepository.selectCount(param);
        if (count == 0) {
            throw new UacBizException(ErrorCodeEnum.UAC10012005, roleId);
        }
    }

    @Override
    public Boolean bindRolePermRelation(Long roleId, List<Long> permIds) {
        roleRepository.deleteRolePermRelationByRoleId(roleId);
        for (Long permId : permIds) {
            roleRepository.insertRolePermRelation(roleId, permId);
        }

        return true;
    }
}
