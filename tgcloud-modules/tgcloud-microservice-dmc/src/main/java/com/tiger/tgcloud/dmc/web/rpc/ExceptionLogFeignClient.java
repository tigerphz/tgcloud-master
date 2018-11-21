package com.tiger.tgcloud.dmc.web.rpc;

import com.tiger.tgcloud.dmc.api.model.dto.GlobalExceptionLogDto;
import com.tiger.tgcloud.dmc.api.service.ExceptionLogFeignApi;
import com.tiger.tgcloud.dmc.service.ExceptionLogService;
import com.tiger.tgcloud.core.support.BaseController;
import com.tiger.tgcloud.utils.wrapper.WrapMapper;
import com.tiger.tgcloud.utils.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/19 10:49
 * @version: V1.0
 * @modified by:
 */
@Slf4j
@RefreshScope
@RestController
@Api(value = "API - DmcExceptionLogFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ExceptionLogFeignClient extends BaseController implements ExceptionLogFeignApi {
    @Resource
    private ExceptionLogService exceptionLogService;

    @Override
    @ApiOperation(httpMethod = "POST", value = "保存日志")
    public Wrapper saveAndSendExceptionLog(@RequestBody GlobalExceptionLogDto exceptionLogDto) {
        try {
            exceptionLogService.saveAndSendExceptionLog(exceptionLogDto);
        } catch (Exception e) {
            log.error("saveAndSendExceptionLog={}", e.getMessage(), e);
        }
        return WrapMapper.ok();
    }
}
