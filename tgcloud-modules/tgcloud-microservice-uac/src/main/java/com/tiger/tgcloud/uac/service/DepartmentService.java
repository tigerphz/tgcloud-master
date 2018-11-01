package com.tiger.tgcloud.uac.service;

import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.uac.model.domain.DepartmentInfo;
import com.tiger.tgcloud.uac.model.query.DepartmentParam;

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
     * 更新部门信息
     * @param departmentInfo
     * @return
     */
    Boolean updateDepartmentStatusById(DepartmentInfo departmentInfo);
}
