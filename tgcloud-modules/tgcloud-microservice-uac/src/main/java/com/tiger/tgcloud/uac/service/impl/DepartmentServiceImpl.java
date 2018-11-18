package com.tiger.tgcloud.uac.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.base.enums.ErrorCodeEnum;
import com.tiger.tgcloud.core.support.BaseService;
import com.tiger.tgcloud.uac.api.exceptions.UacBizException;
import com.tiger.tgcloud.uac.mapping.DepartmentTreeVOMapping;
import com.tiger.tgcloud.uac.model.bo.DepartmentTreeBO;
import com.tiger.tgcloud.uac.model.domain.DepartmentInfo;
import com.tiger.tgcloud.uac.model.query.DepartmentParam;
import com.tiger.tgcloud.uac.repository.DepartmentRepository;
import com.tiger.tgcloud.uac.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private DepartmentTreeVOMapping departmentTreeVOMapping;

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

    /**
     * 查询部门树结构列表
     *
     * @return
     */
    @Override
    public List<DepartmentTreeBO> selectDepartmentTree() {
        List<DepartmentInfo> departmentInfoList = departmentRepository.selectAll();

        //首先找出根节点
        List<DepartmentInfo> topDepartmentInfoList = departmentInfoList.stream()
                .filter(p -> null == p.getParentid()).collect(Collectors.toList());

        List<DepartmentTreeBO> departmentTreeBOList = new ArrayList<>();

        //顶级菜单
        topDepartmentInfoList.stream().forEach(x -> {
            DepartmentTreeBO departmentTreeBO = departmentTreeVOMapping.from(x);
            departmentTreeBO.setChildren(getChildMenu(departmentInfoList, x));

            departmentTreeBOList.add(departmentTreeBO);
        });

        return departmentTreeBOList;
    }

    /**
     * 循环获取子菜单
     *
     * @param departmentInfoList
     * @param departmentInfo
     * @return
     */
    private List<DepartmentTreeBO> getChildMenu(List<DepartmentInfo> departmentInfoList, DepartmentInfo departmentInfo) {
        final List<DepartmentTreeBO> departmentTreeBOList = new ArrayList<>();

        departmentInfoList.stream()
                .filter(p -> !StringUtils.isEmpty(p.getParentid()) &&
                        p.getParentid().equals(departmentInfo.getId()))
                .forEach(x -> {
                    DepartmentTreeBO departmentTreeBO = departmentTreeVOMapping.from(x);
                    departmentTreeBO.setChildren(getChildMenu(departmentInfoList, x));
                    departmentTreeBOList.add(departmentTreeBO);
                });

        return departmentTreeBOList.stream()
                .sorted(Comparator.comparing(DepartmentTreeBO::getSort))
                .collect(Collectors.toList());
    }

    /**
     * 添加部门信息
     *
     * @param departmentInfo
     * @return
     */
    @Override
    public Boolean addDepartment(DepartmentInfo departmentInfo) {
        DepartmentInfo param = new DepartmentInfo();
        param.setDeptname(departmentInfo.getDeptname());
        if (departmentRepository.selectCount(param) > 0) {
            throw new UacBizException(ErrorCodeEnum.UAC10012010);
        }

        departmentInfo.setId(generateId());

        return departmentRepository.save(departmentInfo);
    }

    /**
     * 更新部门信息
     *
     * @param departmentInfo
     * @return
     */
    @Override
    public Boolean updateDepartment(DepartmentInfo departmentInfo) {
        CheckUpdateDepartment(departmentInfo);
        return departmentRepository.updateByPrimaryKeySelective(departmentInfo);
    }

    /**
     * 删除部门信息
     *
     * @param id
     * @return
     */
    @Override
    public Boolean deleteDepartment(Long id) {
        DepartmentInfo departmentInfo = new DepartmentInfo();
        departmentInfo.setId(id);
        CheckUpdateDepartment(departmentInfo);

        //递归删除子项
        deleteDepartmentByParentId(id);

        return departmentRepository.deleteByKey(id);
    }

    /**
     * 递归删除子项
     *
     * @param parentId
     */
    private void deleteDepartmentByParentId(Long parentId) {
        DepartmentInfo where = new DepartmentInfo();
        where.setParentid(parentId);
        List<DepartmentInfo> departmentInfoList = departmentRepository.select(where);
        if (!CollectionUtils.isEmpty(departmentInfoList)) {
            departmentInfoList.forEach(x -> {
                deleteDepartmentByParentId(x.getId());
                departmentRepository.deleteByKey(x.getId());
            });
        }
    }

    private void CheckUpdateDepartment(DepartmentInfo departmentInfo) {
        long deptId = departmentInfo.getId();
        DepartmentInfo param = new DepartmentInfo();
        param.setId(deptId);
        int count = departmentRepository.selectCount(param);
        if (count == 0) {
            throw new UacBizException(ErrorCodeEnum.UAC10012011, deptId);
        }
    }
}
