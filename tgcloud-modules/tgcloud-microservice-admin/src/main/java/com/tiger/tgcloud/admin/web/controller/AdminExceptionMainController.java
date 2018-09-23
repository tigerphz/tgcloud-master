package com.tiger.tgcloud.admin.web.controller;

import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.admin.model.dto.AdminExceptionQueryDto;
import com.tiger.tgcloud.admin.service.AdminExceptionLogService;
import com.tiger.tgcloud.core.support.BaseController;
import com.tiger.tgcloud.utils.wrapper.WrapMapper;
import com.tiger.tgcloud.utils.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/22 17:06
 * @version: V1.0
 * @modified by:
 */
@RestController
@RequestMapping(value = "/exception", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Web - AdminExceptionMainController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdminExceptionMainController extends BaseController {
    @Resource
    private AdminExceptionLogService adminExceptionLogService;

    /**
     * 异常日志列表.
     *
     * @param adminExceptionQueryDto the mdc exception query dto
     * @return the wrapper
     */
    @PostMapping(value = "/queryListWithPage")
    @ApiOperation(httpMethod = "POST", value = "查询日志列表")
    public Wrapper queryLogListWithPage(@ApiParam(name = "mdcExceptionQueryDto", value = "异常查询条件") @RequestBody AdminExceptionQueryDto adminExceptionQueryDto) {
        logger.info("查询日志处理列表 mdcExceptionQueryDto={}", adminExceptionQueryDto);
        PageInfo pageInfo = adminExceptionLogService.queryExceptionListWithPage(adminExceptionQueryDto);
        return WrapMapper.ok(pageInfo);
    }
}

