package com.tiger.tgcloud.dmc.repository.mapper;

import com.tiger.tgcloud.core.mybatis.MyMapper;
import com.tiger.tgcloud.dmc.model.domain.ExceptionLogInfo;
import com.tiger.tgcloud.dmc.model.query.ExceptionLogParam;
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
public interface ExceptionLogMapper extends MyMapper<ExceptionLogInfo> {
    /**
     * Query exception list with page list.
     *
     * @param exceptionLogParam the mdc exception query param
     * @return the list
     */
    List<ExceptionLogInfo> queryExceptionListWithPage(ExceptionLogParam exceptionLogParam);
}
