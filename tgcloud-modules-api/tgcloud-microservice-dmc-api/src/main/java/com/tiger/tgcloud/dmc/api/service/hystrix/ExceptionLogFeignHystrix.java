package com.tiger.tgcloud.dmc.api.service.hystrix;

import com.tiger.tgcloud.dmc.api.model.dto.GlobalExceptionLogDto;
import com.tiger.tgcloud.dmc.api.service.ExceptionLogFeignApi;
import com.tiger.tgcloud.utils.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/19 10:28
 * @version: V1.0
 * @modified by:
 */
@Component
@Slf4j
public class ExceptionLogFeignHystrix implements ExceptionLogFeignApi {

    @Override
    public Wrapper saveAndSendExceptionLog(final GlobalExceptionLogDto exceptionLogDto) {
        log.error("feign client ExceptionLogFeignApi.saveAndSendExceptionLog() fail");
        return null;
    }
}