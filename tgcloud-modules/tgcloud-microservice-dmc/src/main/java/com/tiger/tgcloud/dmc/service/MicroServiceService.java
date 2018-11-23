package com.tiger.tgcloud.dmc.service;

import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.dmc.api.model.domain.MicroServiceInfo;
import com.tiger.tgcloud.dmc.model.query.MicroServiceParam;

/**
 * @description:
 * @author: tiger
 * @date: 2018/11/19 17:36
 * @version: V1.0
 * @modified by:
 */
public interface MicroServiceService {

    /**
     * 分页查询微服务列表
     *
     * @param param
     * @return
     */
    PageInfo<MicroServiceInfo> selectByConditionWithPage(MicroServiceParam param);

    /**
     * 添加微服务信息
     *
     * @param microServiceInfo
     * @return
     */
    Boolean addMicroService(MicroServiceInfo microServiceInfo);

    /**
     * 更新微服务信息
     *
     * @param microServiceInfo
     * @return
     */
    Boolean updateMicroService(MicroServiceInfo microServiceInfo);

    /**
     * 删除微服务信息
     *
     * @param id
     * @return
     */
    Boolean deleteMicroService(Long id);

    /**
     * 更新微服务状态
     *
     * @return
     */
    Boolean updateRoleStatusById(MicroServiceInfo microServiceInfo);
}
