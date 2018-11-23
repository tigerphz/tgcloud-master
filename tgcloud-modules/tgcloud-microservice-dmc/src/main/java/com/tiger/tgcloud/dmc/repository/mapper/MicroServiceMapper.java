package com.tiger.tgcloud.dmc.repository.mapper;

import com.tiger.tgcloud.core.mybatis.MyMapper;
import com.tiger.tgcloud.dmc.api.model.domain.MicroServiceInfo;
import com.tiger.tgcloud.dmc.model.query.MicroServiceParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/11/19 15:27
 * @version: V1.0
 * @modified by:
 */
@Mapper
@Component
public interface MicroServiceMapper extends MyMapper<MicroServiceInfo> {
    /**
     * Query exception list with page list.
     *
     * @param microServiceParam the mdc exception query param
     * @return the list
     */
    List<MicroServiceInfo> selectByCondition(MicroServiceParam microServiceParam);
}
