package com.tiger.tgcloud.dmc.api.service.hystrix;

import com.tiger.tgcloud.dmc.api.model.dto.GlobalExceptionLogDto;
import com.tiger.tgcloud.dmc.api.service.DmcExceptionLogFeignApi;
import com.tiger.tgcloud.utils.wrapper.Wrapper;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/19 10:28
 * @version: V1.0
 * @modified by:
 */
@Component
public class DmcExceptionLogFeignHystrix implements DmcExceptionLogFeignApi {

    @Override
    public Wrapper saveAndSendExceptionLog(final GlobalExceptionLogDto exceptionLogDto) {
        return null;
    }
}