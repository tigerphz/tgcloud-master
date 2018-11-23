package com.tiger.tgcloud.gateway.mapper;

import com.tiger.tgcloud.core.mybatis.MyMapper;
import com.tiger.tgcloud.dmc.api.model.domain.MicroServiceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: tiger
 * @date: 2018/11/22 23:22
 * @version: V1.0
 * @modified by:
 */
@Mapper
@Component
public interface MicroServiceMapper extends MyMapper<MicroServiceInfo> {
}
