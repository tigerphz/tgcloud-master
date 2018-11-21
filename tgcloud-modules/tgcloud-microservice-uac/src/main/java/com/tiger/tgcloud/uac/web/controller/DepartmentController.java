package com.tiger.tgcloud.uac.web.controller;

import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.base.dto.LoginAuthDto;
import com.tiger.tgcloud.core.support.BaseController;
import com.tiger.tgcloud.uac.mapping.DepartmentMapping;
import com.tiger.tgcloud.uac.model.bo.DepartmentTreeBO;
import com.tiger.tgcloud.uac.model.domain.DepartmentInfo;
import com.tiger.tgcloud.uac.model.query.DepartmentParam;
import com.tiger.tgcloud.uac.model.vo.DepartmentVO;
import com.tiger.tgcloud.uac.service.DepartmentService;
import com.tiger.tgcloud.utils.wrapper.WrapMapper;
import com.tiger.tgcloud.utils.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 部门管理
 * @author: tiger
 * @date: 2018/10/24 16:19
 * @version: V1.0
 * @modified by:
 */
@RestController
@RequestMapping(value = "/departments")
@Api(value = "Web - DepartmentController")
public class DepartmentController extends BaseController {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentMapping departmentMapping;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation("获取所有权限信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "param", dataType = "DepartmentParam", value = "获取所有权限信息")
    })
    public Wrapper<PageInfo<DepartmentInfo>> list(DepartmentParam param) {
        PageInfo<DepartmentInfo> permissionInfoPageInfos = departmentService.selectByConditionWithPage(param);

        return WrapMapper.ok(permissionInfoPageInfos);
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    @ApiOperation("获取所有部门树结构信息")
    @ApiImplicitParams({
    })
    public Wrapper<List<DepartmentTreeBO>> tree() {
        List<DepartmentTreeBO> departmentTreeBOList = departmentService.selectDepartmentTree();

        return WrapMapper.ok(departmentTreeBOList);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation("添加部门信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "department", dataType = "DepartmentVO", value = "部门信息")
    })
    public Wrapper<Boolean> insertDepartment(@Valid @RequestBody() DepartmentVO departmentVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldError().getDefaultMessage();
            return WrapMapper.error(message);
        }

        DepartmentInfo departmentInfo = departmentMapping.to(departmentVO);

        LoginAuthDto loginAuthDto = getLoginAuthDto();
        departmentInfo.setUpdateInfo(loginAuthDto);

        return WrapMapper.ok(departmentService.addDepartment(departmentInfo));
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ApiOperation("更新部门信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "departmentVO", dataType = "DepartmentVO", value = "部门信息")
    })
    public Wrapper<Boolean> update(@Valid @RequestBody() DepartmentVO departmentVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getFieldError().getDefaultMessage();
            return WrapMapper.error(message);
        }

        DepartmentInfo departmentInfo = departmentMapping.to(departmentVO);
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        departmentInfo.setUpdateInfo(loginAuthDto);

        return WrapMapper.ok(departmentService.updateDepartment(departmentInfo));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除部门")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", dataType = "Long", value = "Id", required = true),
    })
    public Wrapper<Boolean> updateStatus(@PathVariable(value = "id") Long id) {
        return WrapMapper.ok(departmentService.deleteDepartment(id));
    }
}
