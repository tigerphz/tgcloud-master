package com.tiger.tgcloud.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.admin.api.model.dto.GlobalExceptionLogDto;
import com.tiger.tgcloud.admin.mapper.AdminExceptionLogMapper;
import com.tiger.tgcloud.admin.model.domain.AdminExceptionLog;
import com.tiger.tgcloud.admin.model.dto.AdminExceptionQueryDto;
import com.tiger.tgcloud.admin.service.AdminExceptionLogService;
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
public class AdminExceptionLogServiceImpl extends BaseService<AdminExceptionLog> implements AdminExceptionLogService {
    @Resource
    private AdminExceptionLogMapper adminExceptionLogMapper;

    @Override
    public void saveAndSendExceptionLog(final GlobalExceptionLogDto exceptionLogDto) {
        AdminExceptionLog exceptionLog = new ModelMapper().map(exceptionLogDto, AdminExceptionLog.class);

        exceptionLog.setId(generateId());
        exceptionLog.setCreateTime(new Date());
        adminExceptionLogMapper.insertSelective(exceptionLog);
    }

    @Override
    public PageInfo queryExceptionListWithPage(final AdminExceptionQueryDto adminExceptionQueryDto) {
        PageHelper.startPage(adminExceptionQueryDto.getPageNum(), adminExceptionQueryDto.getPageSize());
        List<AdminExceptionLog> actionList = adminExceptionLogMapper.queryExceptionListWithPage(adminExceptionQueryDto);
        return new PageInfo<>(actionList);
    }
}