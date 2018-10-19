package com.tiger.tgcloud.mapper;

import com.tiger.tgcloud.core.mybatis.MyMapper;
import com.tiger.tgcloud.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/19 14:21
 * @version: V1.0
 * @modified by:
 */
@Mapper
@Component
public interface UserMapper extends MyMapper<UserInfo> {
}

