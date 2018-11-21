package com.tiger.tgcloud.dmc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.core.support.BaseService;
import com.tiger.tgcloud.dmc.api.model.dto.GlobalExceptionLogDto;
import com.tiger.tgcloud.dmc.model.domain.ExceptionLogInfo;
import com.tiger.tgcloud.dmc.model.query.ExceptionLogParam;
import com.tiger.tgcloud.dmc.repository.ExceptionLogRepository;
import com.tiger.tgcloud.dmc.service.ExceptionLogService;
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
public class ExceptionLogServiceImpl extends BaseService implements ExceptionLogService {
    @Resource
    private ExceptionLogRepository exceptionLogRepository;

    @Override
    public void saveAndSendExceptionLog(final GlobalExceptionLogDto exceptionLogDto) {
        ExceptionLogInfo exceptionLog = new ModelMapper().map(exceptionLogDto, ExceptionLogInfo.class);

        exceptionLog.setId(generateId());
        exceptionLog.setCreateTime(new Date());
        exceptionLogRepository.save(exceptionLog);
    }

    @Override
    public PageInfo queryExceptionListWithPage(final ExceptionLogParam exceptionLogParam) {
        PageHelper.startPage(exceptionLogParam.getPageNum(), exceptionLogParam.getPageSize());
        List<ExceptionLogInfo> actionList = exceptionLogRepository.queryExceptionListWithPage(exceptionLogParam);
        return new PageInfo<>(actionList);
    }
}