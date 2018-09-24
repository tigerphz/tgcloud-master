package com.tiger.tgcloud.dmc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.dmc.api.model.dto.GlobalExceptionLogDto;
import com.tiger.tgcloud.dmc.mapper.DmcExceptionLogMapper;
import com.tiger.tgcloud.dmc.model.domain.DmcExceptionLog;
import com.tiger.tgcloud.dmc.model.dto.DmcExceptionQueryDto;
import com.tiger.tgcloud.dmc.service.DmcExceptionLogService;
import com.tiger.tgcloud.core.support.BaseService;
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
public class DmcExceptionLogServiceImpl extends BaseService<DmcExceptionLog> implements DmcExceptionLogService {
    @Resource
    private DmcExceptionLogMapper dmcExceptionLogMapper;

    @Override
    public void saveAndSendExceptionLog(final GlobalExceptionLogDto exceptionLogDto) {
        DmcExceptionLog exceptionLog = new ModelMapper().map(exceptionLogDto, DmcExceptionLog.class);

        exceptionLog.setId(generateId());
        exceptionLog.setCreateTime(new Date());
        dmcExceptionLogMapper.insertSelective(exceptionLog);
    }

    @Override
    public PageInfo queryExceptionListWithPage(final DmcExceptionQueryDto dmcExceptionQueryDto) {
        PageHelper.startPage(dmcExceptionQueryDto.getPageNum(), dmcExceptionQueryDto.getPageSize());
        List<DmcExceptionLog> actionList = dmcExceptionLogMapper.queryExceptionListWithPage(dmcExceptionQueryDto);
        return new PageInfo<>(actionList);
    }
}