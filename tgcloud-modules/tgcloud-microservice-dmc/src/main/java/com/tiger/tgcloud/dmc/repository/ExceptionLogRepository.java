package com.tiger.tgcloud.dmc.repository;

import com.tiger.tgcloud.core.support.BaseRepository;
import com.tiger.tgcloud.dmc.model.domain.ExceptionLogInfo;
import com.tiger.tgcloud.dmc.model.query.ExceptionLogParam;
import com.tiger.tgcloud.dmc.repository.mapper.ExceptionLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/25 22:45
 * @version: V1.0
 * @modified by:
 */
@Repository
public class ExceptionLogRepository extends BaseRepository<ExceptionLogInfo> {
    @Autowired
    private ExceptionLogMapper exceptionLogMapper;

    public List<ExceptionLogInfo> queryExceptionListWithPage(ExceptionLogParam exceptionLogParam) {
        return exceptionLogMapper.queryExceptionListWithPage(exceptionLogParam);
    }
}
