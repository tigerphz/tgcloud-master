package com.tiger.tgcloud.uac.mapping;

import com.tiger.tgcloud.core.support.BasicObjectMapping;
import com.tiger.tgcloud.uac.model.bo.MenuBO;
import com.tiger.tgcloud.uac.model.domain.PermissionInfo;
import org.mapstruct.Mapper;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/26 17:10
 * @version: V1.0
 * @modified by:
 */
@Mapper(componentModel = "spring")
public interface MenuBoMapping extends BasicObjectMapping<MenuBO, PermissionInfo> {
}
