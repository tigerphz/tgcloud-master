package com.tiger.tgcloud.uac.mapping;

import com.tiger.tgcloud.core.support.BasicObjectMapping;
import com.tiger.tgcloud.uac.model.domain.DepartmentInfo;
import com.tiger.tgcloud.uac.model.bo.DepartmentTreeBO;
import org.mapstruct.Mapper;

/**
 * @description:
 * @author: tiger
 * @date: 2018/11/18 17:17
 * @version: V1.0
 * @modified by:
 */
@Mapper(componentModel = "spring")
public interface DepartmentTreeVOMapping extends BasicObjectMapping<DepartmentTreeBO, DepartmentInfo> {
}
