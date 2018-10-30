package com.tiger.tgcloud.core.interceptor;

import com.tiger.tgcloud.base.dto.LoginAuthDto;
import com.tiger.tgcloud.base.dto.UserTokenDto;
import com.tiger.tgcloud.utils.RedisKeyUtil;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/29 17:41
 * @version: V1.0
 * @modified by:
 */
public class RedisTokenInterceptor extends TokenInterceptor {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 通过token获取登陆信息
     * @param token
     * @return
     */
    @Override
    protected LoginAuthDto getLoginAuthDto(String token) {
        logger.info("RedisTokenInterceptor call token={}", token);
        return (UserTokenDto) redisTemplate.opsForValue().get(RedisKeyUtil.getAccessTokenKey(token));
    }
}
