package com.tiger.tgcloud.uac.service;

import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.uac.model.domain.DepartmentInfo;
import com.tiger.tgcloud.uac.model.query.DepartmentParam;
import com.tiger.tgcloud.uac.model.bo.DepartmentTreeBO;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/24 16:46
 * @version: V1.0
 * @modified by:
 */
public interface DepartmentService {
    /**
     * 分页查询部门列表
     *
     * @param param
     * @return
     */
    PageInfo<DepartmentInfo> selectByConditionWithPage(DepartmentParam param);

    /**
     * 查询部门树结构列表
     *
     * @return
     */
    List<DepartmentTreeBO> selectDepartmentTree();

    /**
     * 添加部门信息
     *
     * @param departmentInfo
     * @return
     */
    Boolean addDepartment(DepartmentInfo departmentInfo);

    /**
     * 更新部门信息
     *
     * @param departmentInfo
     * @return
     */
    Boolean updateDepartment(DepartmentInfo departmentInfo);

    /**
     * 删除部门信息
     *
     * @param id
     * @return
     */
    Boolean deleteDepartment(Long id);
}
