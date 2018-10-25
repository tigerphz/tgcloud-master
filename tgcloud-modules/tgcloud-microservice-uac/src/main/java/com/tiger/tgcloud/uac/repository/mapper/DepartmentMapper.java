package com.tiger.tgcloud.uac.repository.mapper;

import com.tiger.tgcloud.core.mybatis.MyMapper;
import com.tiger.tgcloud.uac.model.domain.DepartmentInfo;
import com.tiger.tgcloud.uac.model.query.DepartmentParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/24 16:51
 * @version: V1.0
 * @modified by:
 */
@Mapper
@Component
public interface DepartmentMapper extends MyMapper<DepartmentInfo> {
    /**
     * 根据条件查询部门信息
     *
     * @param param
     * @return
     */
    List<DepartmentInfo> selectByCondition(DepartmentParam param);
}
