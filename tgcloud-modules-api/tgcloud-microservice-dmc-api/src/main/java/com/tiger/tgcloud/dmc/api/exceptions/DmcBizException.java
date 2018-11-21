package com.tiger.tgcloud.dmc.api.exceptions;

import com.tiger.tgcloud.base.enums.ErrorCodeEnum;
import com.tiger.tgcloud.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: tiger
 * @date: 2018/11/19 18:10
 * @version: V1.0
 * @modified by:
 */
@Slf4j
public class DmcBizException extends BusinessException {

    private static final long serialVersionUID = -5799427125257785917L;

    /**
     * Instantiates a new Uac rpc exception.
     */
    public DmcBizException() {
    }

    /**
     * Instantiates a new Uac rpc exception.
     *
     * @param code      the code
     * @param msgFormat the msg format
     * @param args      the args
     */
    public DmcBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
        log.info("<== DmcBizException, code:" + this.code + ", message:" + super.getMessage());

    }

    /**
     * Instantiates a new Uac rpc exception.
     *
     * @param code the code
     * @param msg  the msg
     */
    public DmcBizException(int code, String msg) {
        super(code, msg);
        log.info("<== DmcBizException, code:" + this.code + ", message:" + super.getMessage());
    }

    /**
     * Instantiates a new Uac rpc exception.
     *
     * @param codeEnum the code enum
     */
    public DmcBizException(ErrorCodeEnum codeEnum) {
        super(codeEnum.code(), codeEnum.msg());
        log.info("<== DmcBizException, code:" + this.code + ", message:" + super.getMessage());
    }

    /**
     * Instantiates a new Uac rpc exception.
     *
     * @param codeEnum the code enum
     * @param args     the args
     */
    public DmcBizException(ErrorCodeEnum codeEnum, Object... args) {
        super(codeEnum, args);
        log.info("<== DmcBizException, code:" + this.code + ", message:" + super.getMessage());
    }
}
