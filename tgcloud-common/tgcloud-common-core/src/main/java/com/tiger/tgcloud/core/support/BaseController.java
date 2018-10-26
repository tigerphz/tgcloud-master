package com.tiger.tgcloud.core.support;

import com.tiger.tgcloud.base.constant.GlobalConstant;
import com.tiger.tgcloud.base.dto.LoginAuthDto;
import com.tiger.tgcloud.base.enums.ErrorCodeEnum;
import com.tiger.tgcloud.base.exception.BusinessException;
import com.tiger.tgcloud.utils.PublicUtil;
import com.tiger.tgcloud.utils.ThreadLocalMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/19 10:50
 * @version: V1.0
 * @modified by:
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Gets login auth dto.
     *
     * @return the login auth dto
     */
    protected LoginAuthDto getLoginAuthDto() {
        LoginAuthDto loginAuthDto = (LoginAuthDto) ThreadLocalMap.get(GlobalConstant.Sys.TOKEN_AUTH_DTO);
        if (PublicUtil.isEmpty(loginAuthDto)) {
            throw new BusinessException(ErrorCodeEnum.UAC10011041);
        }
        return loginAuthDto;
    }
}
