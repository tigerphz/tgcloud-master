package com.tiger.tgcloud.admin.api.service.hystrix;

import com.tiger.tgcloud.admin.api.model.dto.GlobalExceptionLogDto;
import com.tiger.tgcloud.admin.api.service.AdminExceptionLogFeignApi;
import com.tiger.tgcloud.common.utils.wrapper.Wrapper;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/19 10:28
 * @version: V1.0
 * @modified by:
 */
@Component
public class AdminExceptionLogFeignHystrix implements AdminExceptionLogFeignApi {

    @Override
    public Wrapper saveAndSendExceptionLog(final GlobalExceptionLogDto exceptionLogDto) {
        return null;
    }
}