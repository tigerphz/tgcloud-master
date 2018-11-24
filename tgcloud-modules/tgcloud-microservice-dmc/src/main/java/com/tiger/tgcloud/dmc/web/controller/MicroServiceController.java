package com.tiger.tgcloud.dmc.web.controller;

import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.base.dto.LoginAuthDto;
import com.tiger.tgcloud.core.support.BaseController;
import com.tiger.tgcloud.dmc.api.model.domain.MicroServiceInfo;
import com.tiger.tgcloud.dmc.model.query.MicroServiceParam;
import com.tiger.tgcloud.dmc.service.MicroServiceService;
import com.tiger.tgcloud.utils.wrapper.WrapMapper;
import com.tiger.tgcloud.utils.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @description:
 * @author: tiger
 * @date: 2018/11/19 17:38
 * @version: V1.0
 * @modified by:
 */
@RestController
@RequestMapping(value = "/microservices")
@Api(value = "Web - MicroServiceController")
public class MicroServiceController extends BaseController {
    @Autowired
    private MicroServiceService microServiceService;

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation("获取微服务信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "param", dataType = "MicroServiceParam", value = "获取所有权限信息")
    })
    public Wrapper<PageInfo<MicroServiceInfo>> list(MicroServiceParam param) {
        PageInfo<MicroServiceInfo> permissionInfoPageInfos = microServiceService.selectByConditionWithPage(param);

        return WrapMapper.ok(permissionInfoPageInfos);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation("添加微服务信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "microServiceInfo", dataType = "MicroServiceInfo", value = "微服务信息")
    })
    public Wrapper<Boolean> insert(@Valid @RequestBody() MicroServiceInfo microServiceInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldError().getDefaultMessage();
            return WrapMapper.error(message);
        }

        LoginAuthDto loginAuthDto = getLoginAuthDto();
        microServiceInfo.setUpdateInfo(loginAuthDto);

        return WrapMapper.ok(microServiceService.addMicroService(microServiceInfo));
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation("更新微服务信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "microServiceInfo", dataType = "MicroServiceInfo", value = "微服务信息")
    })
    public Wrapper<Boolean> update(@Valid @RequestBody() MicroServiceInfo microServiceInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldError().getDefaultMessage();
            return WrapMapper.error(message);
        }

        LoginAuthDto loginAuthDto = getLoginAuthDto();
        microServiceInfo.setUpdateInfo(loginAuthDto);

        return WrapMapper.ok(microServiceService.updateMicroService(microServiceInfo));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除微服务")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "Long", value = "id", required = true),
    })
    public Wrapper<Boolean> delete(@PathVariable(value = "id") Long id) {
        return WrapMapper.ok(microServiceService.deleteMicroService(id));
    }

    @RequestMapping(value = "/{id}/status/{status}", method = RequestMethod.PUT)
    @ApiOperation(value = "更新微服务状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "Long", value = "Id", required = true),
            @ApiImplicitParam(paramType = "path", name = "status", dataType = "Long", value = "状态", required = true)
    })
    public Wrapper<Boolean> updateStatus(@PathVariable(value = "id") Long id, @PathVariable(value = "status") Integer status) {
        MicroServiceInfo microServiceInfo = new MicroServiceInfo();
        microServiceInfo.setId(id);
        microServiceInfo.setStatus(status);

        LoginAuthDto loginAuthDto = getLoginAuthDto();
        microServiceInfo.setUpdateInfo(loginAuthDto);

        return WrapMapper.ok(microServiceService.updateRoleStatusById(microServiceInfo));
    }

    @RequestMapping(value = "/reflesh/routes", method = RequestMethod.PUT)
    @ApiOperation("更新zuul动态路由信息")
    @ApiImplicitParams({
    })
    public Wrapper<Boolean> refleshRoutes() {
        this.rabbitmqTemplate.convertAndSend("amq.topic", "Gateway_RefreshRoutes", "refleshRoutes");

        return WrapMapper.ok();
    }
}
