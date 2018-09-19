package com.tiger.tgcloud.admin.service;

import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.admin.api.model.dto.GlobalExceptionLogDto;
import com.tiger.tgcloud.admin.model.domain.AdminExceptionLog;
import com.tiger.tgcloud.admin.model.dto.AdminExceptionQueryDto;
import com.tiger.tgcloud.common.core.support.IService;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/19 11:44
 * @version: V1.0
 * @modified by:
 */
public interface AdminExceptionLogService extends IService<AdminExceptionLog> {
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
    PageInfo queryExceptionListWithPage(AdminExceptionQueryDto mdcExceptionQueryDto);
}
