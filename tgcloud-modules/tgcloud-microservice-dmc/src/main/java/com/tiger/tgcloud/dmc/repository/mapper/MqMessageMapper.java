package com.tiger.tgcloud.dmc.repository.mapper;

import com.tiger.tgcloud.core.mybatis.MyMapper;
import com.tiger.tgcloud.dmc.api.model.domain.MqMessageData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/20 22:12
 * @version: V1.0
 * @modified by:
 */
@Mapper
@Component
public interface MqMessageMapper extends MyMapper<MqMessageData> {
}
