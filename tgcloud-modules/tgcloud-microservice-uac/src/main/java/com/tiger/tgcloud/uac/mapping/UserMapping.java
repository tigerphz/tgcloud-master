package com.tiger.tgcloud.uac.mapping;

import com.tiger.tgcloud.core.support.BasicObjectMapping;
import com.tiger.tgcloud.uac.api.model.domain.UserInfo;
import com.tiger.tgcloud.uac.model.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/24 17:49
 * @version: V1.0
 * @modified by:
 */
@Mapper(componentModel = "spring")
public interface UserMapping extends BasicObjectMapping<UserVO, UserInfo> {
    @Override
    @Mappings({
            @Mapping(target = "passwordhash", source = "password")
    })
    UserInfo to(UserVO var1);
}
