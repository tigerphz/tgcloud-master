package com.tiger.tgcloud.uac.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.base.enums.ErrorCodeEnum;
import com.tiger.tgcloud.core.support.BaseService;
import com.tiger.tgcloud.uac.api.exceptions.UacBizException;
import com.tiger.tgcloud.uac.mapping.MenuBoMapping;
import com.tiger.tgcloud.uac.model.bo.MenuBO;
import com.tiger.tgcloud.uac.model.bo.RouterMetaBO;
import com.tiger.tgcloud.uac.model.bo.RouterTreeBO;
import com.tiger.tgcloud.uac.model.domain.PermissionInfo;
import com.tiger.tgcloud.uac.model.query.PermissionParam;
import com.tiger.tgcloud.uac.repository.PermissionRepository;
import com.tiger.tgcloud.uac.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/24 16:48
 * @version: V1.0
 * @modified by:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl extends BaseService implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private MenuBoMapping menuBoMapping;

    /**
     * 分页查询用户列表
     *
     * @param param
     * @return
     */
    @Override
    public PageInfo<MenuBO> selectByConditionWithPage(PermissionParam param) {
        if (null != param.getPageNum() && null != param.getPageSize()) {
            PageHelper.startPage(param.getPageNum(), param.getPageSize());
        }

        //首先查询出菜单或者页面
        List<PermissionInfo> topPermissionList = permissionRepository.selectByCondition(param);

        //为了设置总条数等分页信息
        PageInfo pageInfo = new PageInfo<>(topPermissionList);

        topPermissionList = topPermissionList.stream().sorted(Comparator.comparing(PermissionInfo::getSort)).collect(Collectors.toList());

        List<MenuBO> menuBOList = new ArrayList<>();

        //根据页面选中的状态查询所有权限
        PermissionInfo allParam = new PermissionInfo();
        allParam.setStatus(param.getStatus());

        List<PermissionInfo> permissionInfolist = permissionRepository.select(allParam);

        //顶级菜单
        topPermissionList.stream().forEach(x -> {
            MenuBO menuBO = menuBoMapping.from(x);
            menuBO.setChildren(getChildMenu(permissionInfolist, x));

            menuBOList.add(menuBO);
        });

        //设置正真的数据源
        pageInfo.setList(menuBOList.stream()
                .sorted(Comparator.comparing(x -> x.getParentid(), Comparator.nullsFirst(Long::compare)))
                .collect(Collectors.toList()));

        return pageInfo;
    }

    /**
     * 添加权限信息
     *
     * @param permissionInfo
     * @return
     */
    @Override
    public Boolean addPermission(PermissionInfo permissionInfo) {
        PermissionInfo param = new PermissionInfo();

        permissionInfo.setId(generateId());

        return permissionRepository.save(permissionInfo);
    }

    /**
     * 更新部门信息
     *
     * @param permissionInfo
     * @return
     */
    @Override
    public Boolean updatePermission(PermissionInfo permissionInfo) {
        CheckUpdatePermission(permissionInfo);
        return permissionRepository.updateByPrimaryKeySelective(permissionInfo);
    }

    /**
     * 更新部门信息
     *
     * @param permissionInfo
     * @return
     */
    @Override
    public Boolean updatePermissionStatusById(PermissionInfo permissionInfo) {
        CheckUpdatePermission(permissionInfo);
        return permissionRepository.updateByPrimaryKeySelective(permissionInfo);
    }

    /**
     * 删除部门信息
     *
     * @param id
     * @return
     */
    @Override
    public Boolean deletePermission(Long id) {
        PermissionInfo permissionInfo = new PermissionInfo();
        permissionInfo.setId(id);
        CheckUpdatePermission(permissionInfo);

        //递归删除子项
        deletePermissionByParentId(id);

        return permissionRepository.deleteByKey(id);
    }

    /**
     * 递归删除子项
     *
     * @param parentId
     */
    private void deletePermissionByParentId(Long parentId) {
        PermissionInfo where = new PermissionInfo();
        where.setParentid(parentId);
        List<PermissionInfo> permissionInfoList = permissionRepository.select(where);
        if (!CollectionUtils.isEmpty(permissionInfoList)) {
            permissionInfoList.forEach(x -> {
                deletePermissionByParentId(x.getId());
                permissionRepository.deleteByKey(x.getId());

            });
        }
    }

    private void CheckUpdatePermission(PermissionInfo permissionInfo) {
        long roleId = permissionInfo.getId();
        PermissionInfo param = new PermissionInfo();
        param.setId(roleId);
        int count = permissionRepository.selectCount(param);
        if (count == 0) {
            throw new UacBizException(ErrorCodeEnum.UAC10012012, roleId);
        }
    }

    /**
     * 查询权限返回树结构
     *
     * @return
     */
    @Override
    public List<RouterTreeBO> selectRouterTree() {
        List<MenuBO> menuBOList = new ArrayList<>();
        Example example = new Example(PermissionInfo.class);
        example.createCriteria().andEqualTo("isnavigate", true);
        List<PermissionInfo> permissionInfolist = permissionRepository.selectByExample(example);

        List<PermissionInfo> topPermissionList = permissionInfolist.stream()
                .filter(p -> null == p.getParentid()).collect(Collectors.toList());

        //顶级菜单
        topPermissionList.stream().forEach(x -> {
            MenuBO menuBO = menuBoMapping.from(x);
            menuBO.setChildren(getChildMenu(permissionInfolist, x));

            menuBOList.add(menuBO);
        });

        List<RouterTreeBO> routerTreeBOList = new ArrayList<>();

        if (CollectionUtils.isEmpty(menuBOList)) {
            return routerTreeBOList;
        }

        routerTreeBOList = toRouterTreeBO(menuBOList);

        return routerTreeBOList;
    }

    private List<RouterTreeBO> toRouterTreeBO(List<MenuBO> menuBOList) {
        List<RouterTreeBO> routerTreeBOList = new ArrayList<>();

        menuBOList.forEach(x -> {
            RouterTreeBO routerTreeBO = new RouterTreeBO();
            routerTreeBO.setName(x.getName());
            routerTreeBO.setComponent(x.getComponent());
            routerTreeBO.setPath(x.getPath());
            routerTreeBO.setMeta(new RouterMetaBO(x.getIcon(), x.getTitle(), x.getCode()));
            if (!CollectionUtils.isEmpty(x.getChildren())) {
                routerTreeBO.setChildren(toRouterTreeBO(x.getChildren()));
            }
            if (x.getIsplugin()) {
                Map<String, String> map = new HashMap<>(2);
                map.put("url", x.getUrl());
                map.put("name", x.getTitle());
                routerTreeBO.setProps(map);
            }

            routerTreeBOList.add(routerTreeBO);
        });

        return routerTreeBOList;
    }

    /**
     * 循环获取子菜单
     *
     * @param permissionInfolist
     * @param permissionInfo
     * @return
     */
    private List<MenuBO> getChildMenu(List<PermissionInfo> permissionInfolist, PermissionInfo permissionInfo) {
        final List<MenuBO> menuBOList = new ArrayList<>();

        permissionInfolist.stream()
                .filter(p -> !StringUtils.isEmpty(p.getParentid()) &&
                        p.getParentid().equals(permissionInfo.getId()))
                .forEach(x -> {
                    MenuBO menuBO = menuBoMapping.from(x);
                    menuBO.setChildren(getChildMenu(permissionInfolist, x));
                    menuBOList.add(menuBO);
                });

        return menuBOList.stream()
                .sorted(Comparator.comparing(MenuBO::getSort))
                .collect(Collectors.toList());
    }

    /**
     * 获取用户的菜单
     *
     * @param userId
     * @return
     */
    @Override
    public List<RouterTreeBO> getRouterByUserId(long userId) {
        List<MenuBO> menuBOList = new ArrayList<>();

        //查找用户的权限
        List<PermissionInfo> permissionInfolist = permissionRepository.selectByUserId(userId);

        //查询所有正常权限
        PermissionInfo allParam = new PermissionInfo();
        allParam.setStatus(0);

        Example example = new Example(PermissionInfo.class);
        example.createCriteria().andEqualTo("isnavigate", true)
                .orIn("type", Arrays.asList(0, 1));

        List<PermissionInfo> allPermissionInfolist = permissionRepository.selectByExample(example);

        //找出导航权限的父节点，父节点可能没有保存到角色权限关系表中
        getParentPermission(allPermissionInfolist, permissionInfolist);

        //获取导航权限
        List<PermissionInfo> routerPermissionList = permissionInfolist.stream().filter(x -> x.getIsnavigate() == true)
                .distinct()
                .collect(Collectors.toList());

        List<PermissionInfo> topPermissionList = routerPermissionList.stream()
                .filter(p -> null == p.getParentid())
                .sorted(Comparator.comparing(PermissionInfo::getSort))
                .collect(Collectors.toList());

        //顶级菜单
        topPermissionList.stream().forEach(x -> {
            MenuBO menuBO = menuBoMapping.from(x);
            menuBO.setChildren(getChildMenu(routerPermissionList, x));

            menuBOList.add(menuBO);
        });

        List<RouterTreeBO> routerTreeBOList = new ArrayList<>();

        if (CollectionUtils.isEmpty(menuBOList)) {
            return routerTreeBOList;
        }

        routerTreeBOList = toRouterTreeBO(menuBOList);

        return routerTreeBOList;
    }

    private void getParentPermission(List<PermissionInfo> allPermissionInfolist, List<PermissionInfo> routerPermissionList) {
        List<PermissionInfo> parentPermissionList = new ArrayList<>();

        routerPermissionList.stream().map(x -> x.getParentid()).distinct()
                .forEach(x -> {
                    allPermissionInfolist.stream()
                            .filter(y -> y.getId().equals(x))
                            .findFirst()
                            .ifPresent(y -> {
                                parentPermissionList.add(y);
                            });
                });

        if (CollectionUtils.isEmpty(parentPermissionList)) {
            return;
        }

        getParentPermission(allPermissionInfolist, parentPermissionList);

        routerPermissionList.addAll(parentPermissionList);
    }

    /**
     * 获取角色拥有的权限
     *
     * @param roleId
     * @return
     */
    @Override
    public List<PermissionInfo> selectByRoleId(Long roleId) {
        return permissionRepository.selectByRoleId(roleId);
    }

    /**
     * 获取所有节点数据不包括按钮
     *
     * @return
     */
    @Override
    public List<PermissionInfo> selectMenuNode() {
        return permissionRepository.selectMenuNode();
    }
}
