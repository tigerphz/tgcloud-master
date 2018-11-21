package com.tiger.tgcloud.dmc.service;

import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.dmc.model.domain.MicroServiceInfo;
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
     * 分页查询部门列表
     *
     * @param param
     * @return
     */
    PageInfo<MicroServiceInfo> selectByConditionWithPage(MicroServiceParam param);

    /**
     * 添加部门信息
     *
     * @param microServiceInfo
     * @return
     */
    Boolean addMicroService(MicroServiceInfo microServiceInfo);

    /**
     * 更新部门信息
     *
     * @param microServiceInfo
     * @return
     */
    Boolean updateMicroService(MicroServiceInfo microServiceInfo);

    /**
     * 删除部门信息
     *
     * @param id
     * @return
     */
    Boolean deleteMicroService(Long id);
}
