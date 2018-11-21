package com.tiger.tgcloud.dmc.api.service;

import com.tiger.tgcloud.dmc.api.model.dto.GlobalExceptionLogDto;
import com.tiger.tgcloud.dmc.api.service.hystrix.ExceptionLogFeignHystrix;
import com.tiger.tgcloud.security.feign.OAuth2FeignAutoConfiguration;
import com.tiger.tgcloud.utils.wrapper.Wrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/19 10:27
 * @version: V1.0
 * @modified by:
 */
@FeignClient(value = "tgcloud-microservice-dmc", configuration = OAuth2FeignAutoConfiguration.class, fallback = ExceptionLogFeignHystrix.class)
public interface ExceptionLogFeignApi {

    /**
     * 添加异常日志
     *
     * @param exceptionLogDto the exception log dto
     * @return the int
     */
    @PostMapping(value = "/api/exception/saveAndSendExceptionLog")
    Wrapper saveAndSendExceptionLog(@RequestBody GlobalExceptionLogDto exceptionLogDto);

}
