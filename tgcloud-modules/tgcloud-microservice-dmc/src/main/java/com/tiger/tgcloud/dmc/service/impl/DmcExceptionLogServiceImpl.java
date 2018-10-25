package com.tiger.tgcloud.dmc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.core.support.BaseService;
import com.tiger.tgcloud.dmc.api.model.dto.GlobalExceptionLogDto;
import com.tiger.tgcloud.dmc.model.domain.DmcExceptionLog;
import com.tiger.tgcloud.dmc.model.dto.DmcExceptionQueryConditionDto;
import com.tiger.tgcloud.dmc.repository.DmcExceptionLogRepository;
import com.tiger.tgcloud.dmc.service.DmcExceptionLogService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/19 11:50
 * @version: V1.0
 * @modified by:
 */
@Service
public class DmcExceptionLogServiceImpl extends BaseService implements DmcExceptionLogService {
    @Resource
    private DmcExceptionLogRepository dmcExceptionLogRepository;

    @Override
    public void saveAndSendExceptionLog(final GlobalExceptionLogDto exceptionLogDto) {
        DmcExceptionLog exceptionLog = new ModelMapper().map(exceptionLogDto, DmcExceptionLog.class);

        exceptionLog.setId(generateId());
        exceptionLog.setCreateTime(new Date());
        dmcExceptionLogRepository.save(exceptionLog);
    }

    @Override
    public PageInfo queryExceptionListWithPage(final DmcExceptionQueryConditionDto dmcExceptionQueryDto) {
        PageHelper.startPage(dmcExceptionQueryDto.getPageNum(), dmcExceptionQueryDto.getPageSize());
        List<DmcExceptionLog> actionList = dmcExceptionLogRepository.queryExceptionListWithPage(dmcExceptionQueryDto);
        return new PageInfo<>(actionList);
    }
}