package com.tiger.tgcloud.admin.mapper;

import com.tiger.tgcloud.admin.model.domain.AdminExceptionLog;
import com.tiger.tgcloud.admin.model.dto.AdminExceptionQueryDto;
import com.tiger.tgcloud.core.mybatis.MyMapper;
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
public interface AdminExceptionLogMapper extends MyMapper<AdminExceptionLog> {
    /**
     * Query exception list with page list.
     *
     * @param mdcExceptionQueryDto the mdc exception query dto
     * @return the list
     */
    List<AdminExceptionLog> queryExceptionListWithPage(AdminExceptionQueryDto mdcExceptionQueryDto);
}
