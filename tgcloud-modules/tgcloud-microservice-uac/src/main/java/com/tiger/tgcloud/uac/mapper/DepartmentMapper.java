package com.tiger.tgcloud.uac.mapper;

import com.tiger.tgcloud.core.mybatis.MyMapper;
import com.tiger.tgcloud.uac.model.domain.DepartmentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

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
}
