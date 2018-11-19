package com.tiger.tgcloud.dmc.web.controller;

import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.core.support.BaseController;
import com.tiger.tgcloud.dmc.model.query.DmcExceptionParam;
import com.tiger.tgcloud.dmc.service.DmcExceptionLogService;
import com.tiger.tgcloud.utils.wrapper.WrapMapper;
import com.tiger.tgcloud.utils.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api(value = "Web - DmcExceptionController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DmcExceptionController extends BaseController {
    @Resource
    private DmcExceptionLogService dmcExceptionLogService;

    /**
     * 异常日志列表.
     *
     * @param dmcExceptionParam the mdc exception query param
     * @return the wrapper
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "查询日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "param", dataType = "DmcExceptionParam", value = "查询日志列表信息")
    })
    public Wrapper queryLogListWithPage(DmcExceptionParam dmcExceptionParam) {
        PageInfo pageInfo = dmcExceptionLogService.queryExceptionListWithPage(dmcExceptionParam);
        return WrapMapper.ok(pageInfo);
    }
}

