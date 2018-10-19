package com.tiger.tgcloud.uac.api.exceptions;

import com.tiger.tgcloud.base.enums.ErrorCodeEnum;
import com.tiger.tgcloud.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/19 10:24
 * @version: V1.0
 * @modified by:
 */
@Slf4j
public class UacBizException extends BusinessException {

    private static final long serialVersionUID = -7468932601808705921L;

    /**
     * Instantiates a new Uac rpc exception.
     */
    public UacBizException() {
    }

    /**
     * Instantiates a new Uac rpc exception.
     *
     * @param code      the code
     * @param msgFormat the msg format
     * @param args      the args
     */
    public UacBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
        log.info("<== UacRpcException, code:" + this.code + ", message:" + super.getMessage());

    }

    /**
     * Instantiates a new Uac rpc exception.
     *
     * @param code the code
     * @param msg  the msg
     */
    public UacBizException(int code, String msg) {
        super(code, msg);
        log.info("<== UacRpcException, code:" + this.code + ", message:" + super.getMessage());
    }

    /**
     * Instantiates a new Uac rpc exception.
     *
     * @param codeEnum the code enum
     */
    public UacBizException(ErrorCodeEnum codeEnum) {
        super(codeEnum.code(), codeEnum.msg());
        log.info("<== UacRpcException, code:" + this.code + ", message:" + super.getMessage());
    }

    /**
     * Instantiates a new Uac rpc exception.
     *
     * @param codeEnum the code enum
     * @param args     the args
     */
    public UacBizException(ErrorCodeEnum codeEnum, Object... args) {
        super(codeEnum, args);
        log.info("<== OpcRpcException, code:" + this.code + ", message:" + super.getMessage());
    }
}
