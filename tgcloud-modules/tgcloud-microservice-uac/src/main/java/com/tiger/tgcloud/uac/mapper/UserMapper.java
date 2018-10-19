package com.tiger.tgcloud.uac.mapper;

import com.tiger.tgcloud.core.mybatis.MyMapper;
import com.tiger.tgcloud.uac.model.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/17 10:21
 * @version: V1.0
 * @modified by:
 */
@Mapper
@Component
public interface UserMapper extends MyMapper<UserInfo> {
}
