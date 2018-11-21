package com.tiger.tgcloud.dmc.web.controller;

import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.core.support.BaseController;
import com.tiger.tgcloud.dmc.model.query.ExceptionLogParam;
import com.tiger.tgcloud.dmc.service.ExceptionLogService;
import com.tiger.tgcloud.utils.wrapper.WrapMapper;
import com.tiger.tgcloud.utils.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping(value = "/exceptions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Web - ExceptionLogController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ExceptionLogController extends BaseController {
    @Resource
    private ExceptionLogService exceptionLogService;

    /**
     * 异常日志列表.
     *
     * @param exceptionLogParam the mdc exception query param
     * @return the wrapper
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "查询日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "param", dataType = "ExceptionLogParam", value = "查询日志列表信息")
    })
    public Wrapper queryLogListWithPage(ExceptionLogParam exceptionLogParam) {
        PageInfo pageInfo = exceptionLogService.queryExceptionListWithPage(exceptionLogParam);
        return WrapMapper.ok(pageInfo);
    }
}

