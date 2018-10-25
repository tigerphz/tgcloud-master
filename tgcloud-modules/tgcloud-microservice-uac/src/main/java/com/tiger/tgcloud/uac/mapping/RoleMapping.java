package com.tiger.tgcloud.uac.mapping;

import com.tiger.tgcloud.core.support.BasicObjectMapping;
import com.tiger.tgcloud.uac.model.domain.RoleInfo;
import com.tiger.tgcloud.uac.model.vo.RoleVO;
import org.mapstruct.Mapper;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/24 17:55
 * @version: V1.0
 * @modified by:
 */
@Mapper(componentModel = "spring")
public interface RoleMapping extends BasicObjectMapping<RoleVO, RoleInfo> {
}
