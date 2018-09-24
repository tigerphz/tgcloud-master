package com.tiger.tgcloud.dmc.web.controller;

import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.dmc.model.dto.DmcExceptionQueryDto;
import com.tiger.tgcloud.dmc.service.DmcExceptionLogService;
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
@Api(value = "Web - DmcExceptionMainController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DmcExceptionMainController extends BaseController {
    @Resource
    private DmcExceptionLogService dmcExceptionLogService;

    /**
     * 异常日志列表.
     *
     * @param dmcExceptionQueryDto the mdc exception query dto
     * @return the wrapper
     */
    @PostMapping(value = "/queryListWithPage")
    @ApiOperation(httpMethod = "POST", value = "查询日志列表")
    public Wrapper queryLogListWithPage(@ApiParam(name = "mdcExceptionQueryDto", value = "异常查询条件") @RequestBody DmcExceptionQueryDto dmcExceptionQueryDto) {
        logger.info("查询日志处理列表 mdcExceptionQueryDto={}", dmcExceptionQueryDto);
        PageInfo pageInfo = dmcExceptionLogService.queryExceptionListWithPage(dmcExceptionQueryDto);
        return WrapMapper.ok(pageInfo);
    }
}

