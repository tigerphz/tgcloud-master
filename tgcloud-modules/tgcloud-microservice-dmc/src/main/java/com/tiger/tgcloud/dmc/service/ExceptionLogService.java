package com.tiger.tgcloud.dmc.service;

import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.dmc.api.model.dto.GlobalExceptionLogDto;
import com.tiger.tgcloud.dmc.model.query.ExceptionLogParam;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/19 11:44
 * @version: V1.0
 * @modified by:
 */
public interface ExceptionLogService {
    /**
     * 保存日志并发送钉钉消息.
     *
     * @param exceptionLogDto the exception log dto
     */
    void saveAndSendExceptionLog(GlobalExceptionLogDto exceptionLogDto);

    /**
     * Query exception list with page page info.
     *
     * @param mdcExceptionQueryDto the mdc exception query dto
     * @return the page info
     */
    PageInfo queryExceptionListWithPage(ExceptionLogParam mdcExceptionQueryDto);
}
