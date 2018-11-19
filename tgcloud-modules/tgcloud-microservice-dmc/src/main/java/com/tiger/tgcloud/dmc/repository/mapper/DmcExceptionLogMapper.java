package com.tiger.tgcloud.dmc.repository.mapper;

import com.tiger.tgcloud.core.mybatis.MyMapper;
import com.tiger.tgcloud.dmc.model.domain.DmcExceptionLog;
import com.tiger.tgcloud.dmc.model.query.DmcExceptionParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/19 12:08
 * @version: V1.0
 * @modified by:
 */
@Mapper
@Component
public interface DmcExceptionLogMapper extends MyMapper<DmcExceptionLog> {
    /**
     * Query exception list with page list.
     *
     * @param dmcExceptionQueryDto the mdc exception query dto
     * @return the list
     */
    List<DmcExceptionLog> queryExceptionListWithPage(DmcExceptionParam dmcExceptionQueryDto);
}
