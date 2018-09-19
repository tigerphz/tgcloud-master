package com.tiger.tgcloud.common.core.support;

import com.tiger.tgcloud.common.base.constant.GlobalConstant;
import com.tiger.tgcloud.common.base.dto.LoginAuthDto;
import com.tiger.tgcloud.common.base.enums.ErrorCodeEnum;
import com.tiger.tgcloud.common.base.exception.BusinessException;
import com.tiger.tgcloud.common.core.security.SnowflakeIdWorker;
import com.tiger.tgcloud.common.utils.PublicUtil;
import com.tiger.tgcloud.common.utils.ThreadLocalMap;
import com.tiger.tgcloud.common.utils.wrapper.WrapMapper;
import com.tiger.tgcloud.common.utils.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/19 10:50
 * @version: V1.0
 * @modified by:
 */
@Slf4j
public class BaseController {

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

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

    /**
     * Handle result wrapper.
     *
     * @param <T>    the type parameter
     * @param result the result
     * @return the wrapper
     */
    protected <T> Wrapper<T> handleResult(T result) {
        boolean flag = isFlag(result);

        if (flag) {
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "操作成功", result);
        } else {
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "操作失败", result);
        }
    }

    /**
     * Handle result wrapper.
     *
     * @param <E>      the type parameter
     * @param result   the result
     * @param errorMsg the error msg
     * @return the wrapper
     */
    protected <E> Wrapper<E> handleResult(E result, String errorMsg) {
        boolean flag = isFlag(result);

        if (flag) {
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "操作成功", result);
        } else {
            return WrapMapper.wrap(Wrapper.ERROR_CODE, errorMsg, result);
        }
    }

    private boolean isFlag(Object result) {
        boolean flag;
        if (result instanceof Integer) {
            flag = (Integer) result > 0;
        } else if (result instanceof Boolean) {
            flag = (Boolean) result;
        } else {
            flag = PublicUtil.isNotEmpty(result);
        }
        return flag;
    }

    protected long generateId() {
        return snowflakeIdWorker.nextId();
    }
}
