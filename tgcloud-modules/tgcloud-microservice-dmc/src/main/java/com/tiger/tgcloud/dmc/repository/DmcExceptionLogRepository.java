package com.tiger.tgcloud.dmc.repository;

import com.tiger.tgcloud.core.support.BaseRepository;
import com.tiger.tgcloud.dmc.model.domain.DmcExceptionLog;
import com.tiger.tgcloud.dmc.model.dto.DmcExceptionQueryConditionDto;
import com.tiger.tgcloud.dmc.repository.mapper.DmcExceptionLogMapper;
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
public class DmcExceptionLogRepository extends BaseRepository<DmcExceptionLog> {
    @Autowired
    private DmcExceptionLogMapper dmcExceptionLogMapper;

    public List<DmcExceptionLog> queryExceptionListWithPage(DmcExceptionQueryConditionDto dmcExceptionQueryDto) {
        return dmcExceptionLogMapper.queryExceptionListWithPage(dmcExceptionQueryDto);
    }
}
