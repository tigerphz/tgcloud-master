package com.tiger.tgcloud.uac.mapping;

import com.tiger.tgcloud.core.support.BasicObjectMapping;
import com.tiger.tgcloud.uac.model.domain.DepartmentInfo;
import com.tiger.tgcloud.uac.model.vo.DepartmentVO;
import org.mapstruct.Mapper;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/24 17:57
 * @version: V1.0
 * @modified by:
 */
@Mapper(componentModel = "spring")
public interface DepartmentMapping extends BasicObjectMapping<DepartmentVO, DepartmentInfo> {
}
