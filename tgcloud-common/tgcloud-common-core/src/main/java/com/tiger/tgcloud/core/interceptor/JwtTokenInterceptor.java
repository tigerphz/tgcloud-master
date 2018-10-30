package com.tiger.tgcloud.core.interceptor;

import com.tiger.tgcloud.base.dto.LoginAuthDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Map;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/29 17:48
 * @version: V1.0
 * @modified by:
 */
public class JwtTokenInterceptor extends TokenInterceptor {
    @Value("${tgcloud.oauth2.jwtSigningKey}")
    private String signingKey;

    /**
     * 通过token获取登陆信息
     *
     * @param token
     * @return
     */
    @Override
    protected LoginAuthDto getLoginAuthDto(String token) {
        logger.info("JwtTokenInterceptor call token={}", token);

        String skey = signingKey;
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Key signingKey = new SecretKeySpec(skey.getBytes(), signatureAlgorithm.getJcaName());

        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }

        LoginAuthDto loginAuthDto = getLoginAuthDtoFromToken(claims);

        return loginAuthDto;
    }

    private LoginAuthDto getLoginAuthDtoFromToken(Map<String, Object> claims) {
        LoginAuthDto loginAuthDto = new LoginAuthDto();
        loginAuthDto.setUserId(Long.valueOf(claims.get("uid").toString()));
        loginAuthDto.setUserName(StringUtils.isEmpty(claims.get("uname")) ? "" : claims.get("uname").toString());
        loginAuthDto.setNickName(StringUtils.isEmpty(claims.get("nick")) ? "" : claims.get("nick").toString());
        if (!StringUtils.isEmpty(claims.get("gid"))) {
            loginAuthDto.setDeptId(Long.valueOf(claims.get("gid").toString()));
        }
        loginAuthDto.setDeptName(StringUtils.isEmpty(claims.get("gname")) ? "" : claims.get("gname").toString());

        return loginAuthDto;
    }
}
