package com.tiger.tgcloud.admin.api.service;

import com.tiger.tgcloud.admin.api.model.dto.GlobalExceptionLogDto;
import com.tiger.tgcloud.admin.api.service.hystrix.AdminExceptionLogFeignHystrix;
import com.tiger.tgcloud.utils.wrapper.Wrapper;
import com.tiger.tgcloud.security.feign.OAuth2FeignAutoConfiguration;
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
@FeignClient(value = "tgcloud-system-admin", configuration = OAuth2FeignAutoConfiguration.class, fallback = AdminExceptionLogFeignHystrix.class)
public interface AdminExceptionLogFeignApi {

    /**
     * Update product stock by id int.
     *
     * @param exceptionLogDto the exception log dto
     * @return the int
     */
    @PostMapping(value = "/api/exception/saveAndSendExceptionLog")
    Wrapper saveAndSendExceptionLog(@RequestBody GlobalExceptionLogDto exceptionLogDto);
}
