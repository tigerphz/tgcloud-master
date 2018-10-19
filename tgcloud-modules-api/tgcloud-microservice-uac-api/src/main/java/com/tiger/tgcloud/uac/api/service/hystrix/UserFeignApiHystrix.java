package com.tiger.tgcloud.uac.api.service.hystrix;

import com.tiger.tgcloud.uac.api.model.dto.UserInfoDto;
import com.tiger.tgcloud.uac.api.service.UserFeignApi;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/19 11:28
 * @version: V1.0
 * @modified by:
 */
@Component
public class UserFeignApiHystrix implements UserFeignApi {
    @Override
    public UserInfoDto findUserInfoByUserId(Long userId) {
        return null;
    }
}
