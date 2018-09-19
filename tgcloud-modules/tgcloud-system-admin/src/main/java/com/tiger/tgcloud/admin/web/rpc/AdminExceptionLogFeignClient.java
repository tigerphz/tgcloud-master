package com.tiger.tgcloud.admin.web.rpc;

import com.tiger.tgcloud.admin.api.model.dto.GlobalExceptionLogDto;
import com.tiger.tgcloud.admin.api.service.AdminExceptionLogFeignApi;
import com.tiger.tgcloud.admin.service.AdminExceptionLogService;
import com.tiger.tgcloud.common.core.support.BaseController;
import com.tiger.tgcloud.common.utils.wrapper.WrapMapper;
import com.tiger.tgcloud.common.utils.wrapper.Wrapper;
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
@Api(value = "API - AdminExceptionLogFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdminExceptionLogFeignClient extends BaseController implements AdminExceptionLogFeignApi {
    @Resource
    private AdminExceptionLogService mdcExceptionLogService;

    @Override
    @ApiOperation(httpMethod = "POST", value = "保存日志并发送钉钉消息")
    public Wrapper saveAndSendExceptionLog(@RequestBody GlobalExceptionLogDto exceptionLogDto) {
        try {
            mdcExceptionLogService.saveAndSendExceptionLog(exceptionLogDto);
        } catch (Exception e) {
            log.error("saveAndSendExceptionLog={}", e.getMessage(), e);
        }
        return WrapMapper.ok();
    }
}
