package com.tiger.tgcloud.uac.api.service;

import com.tiger.tgcloud.security.feign.OAuth2FeignAutoConfiguration;
import com.tiger.tgcloud.uac.api.model.dto.UserInfoDto;
import com.tiger.tgcloud.uac.api.service.hystrix.UserFeignApiHystrix;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/19 11:28
 * @version: V1.0
 * @modified by:
 */
@FeignClient(value = "tgcloud-microservice-uac", configuration = OAuth2FeignAutoConfiguration.class, fallback = UserFeignApiHystrix.class)
public interface UserFeignApi {

    /**
     * 根据用户ID查询用户信息.
     *
     * @param userId
     * @return
     */
    @PostMapping(value = "/api/uac/user/{userId}")
    UserInfoDto findUserInfoByUserId(@Param("userId") Long userId);
}
