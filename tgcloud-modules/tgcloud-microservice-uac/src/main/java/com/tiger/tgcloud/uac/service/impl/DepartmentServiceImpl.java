package com.tiger.tgcloud.uac.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.core.support.BaseService;
import com.tiger.tgcloud.uac.model.domain.DepartmentInfo;
import com.tiger.tgcloud.uac.model.query.DepartmentParam;
import com.tiger.tgcloud.uac.repository.DepartmentRepository;
import com.tiger.tgcloud.uac.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/24 16:49
 * @version: V1.0
 * @modified by:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DepartmentServiceImpl extends BaseService implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * 分页查询部门列表
     *
     * @param param
     * @return
     */
    @Override
    public PageInfo<DepartmentInfo> selectByConditionWithPage(DepartmentParam param) {
        if (null != param.getPageNum() && null != param.getPageSize()) {
            PageHelper.startPage(param.getPageNum(), param.getPageSize());
        }

        List<DepartmentInfo> departmentInfos = departmentRepository.selectByCondition(param);
        return new PageInfo<>(departmentInfos);
    }
}
