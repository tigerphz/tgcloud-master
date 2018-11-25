package com.tiger.tgcloud.dmc.web;

import com.tiger.tgcloud.base.enums.ErrorCodeEnum;
import com.tiger.tgcloud.base.exception.BusinessException;
import com.tiger.tgcloud.dmc.api.model.dto.GlobalExceptionLogDto;
import com.tiger.tgcloud.dmc.api.service.ExceptionLogFeignApi;
import com.tiger.tgcloud.utils.wrapper.WrapMapper;
import com.tiger.tgcloud.utils.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/19 9:10
 * @version: V1.0
 * @modified by:
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @Resource
    private TaskExecutor taskExecutor;
    @Value("${spring.profiles.active}")
    String profile;
    @Value("${spring.application.name}")
    String applicationName;
    @Resource
    private ExceptionLogFeignApi exceptionLogFeignApi;

    /**
     * 参数非法异常.
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Wrapper illegalArgumentException(IllegalArgumentException e) {
        log.error("参数非法异常={}", e.getMessage());
        return WrapMapper.wrap(ErrorCodeEnum.GL99990100.code(), e.getMessage());
    }

    /**
     * 业务异常.
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Wrapper businessException(BusinessException e) {
        log.error("业务异常={}", e.getMessage());
        return WrapMapper.wrap(e.getCode() == 0 ? Wrapper.ERROR_CODE : e.getCode(), e.getMessage());
    }


    /**
     * 全局异常.
     *
     * @param e the e
     * @return the wrapper
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Wrapper exception(Exception e) {
        log.info("保存全局异常信息 ex={}", e.getMessage());
        GlobalExceptionLogDto globalExceptionLogDto = new GlobalExceptionLogDto().getGlobalExceptionLogDto(e, profile, applicationName);
        taskExecutor.execute(() -> exceptionLogFeignApi.saveAndSendExceptionLog(globalExceptionLogDto));
        return WrapMapper.error();
    }
}
