package com.tiger.tgcloud.uac.repository;

import com.tiger.tgcloud.core.support.BaseRepository;
import com.tiger.tgcloud.uac.model.domain.DepartmentInfo;
import com.tiger.tgcloud.uac.model.query.DepartmentParam;
import com.tiger.tgcloud.uac.repository.mapper.DepartmentMapper;
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
public class DepartmentRepository extends BaseRepository<DepartmentInfo> {
    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 根据条件查询用户信息
     *
     * @param param
     * @return
     */
    public List<DepartmentInfo> selectByCondition(DepartmentParam param) {
        return departmentMapper.selectByCondition(param);
    }
}