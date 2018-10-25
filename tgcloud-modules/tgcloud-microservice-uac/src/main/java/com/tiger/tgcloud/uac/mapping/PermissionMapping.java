package com.tiger.tgcloud.uac.mapping;

import com.tiger.tgcloud.core.support.BasicObjectMapping;
import com.tiger.tgcloud.uac.model.domain.PermissionInfo;
import com.tiger.tgcloud.uac.model.vo.PermissionVO;
import org.mapstruct.Mapper;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/24 17:56
 * @version: V1.0
 * @modified by:
 */
@Mapper(componentModel = "spring")
public interface PermissionMapping extends BasicObjectMapping<PermissionVO, PermissionInfo> {
}
