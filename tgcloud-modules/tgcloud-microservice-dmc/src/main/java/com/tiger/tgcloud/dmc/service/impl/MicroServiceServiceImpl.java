package com.tiger.tgcloud.dmc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiger.tgcloud.base.enums.ErrorCodeEnum;
import com.tiger.tgcloud.core.support.BaseService;
import com.tiger.tgcloud.dmc.api.exceptions.DmcBizException;
import com.tiger.tgcloud.dmc.api.model.domain.MicroServiceInfo;
import com.tiger.tgcloud.dmc.model.query.MicroServiceParam;
import com.tiger.tgcloud.dmc.repository.MicroServiceRepository;
import com.tiger.tgcloud.dmc.service.MicroServiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/11/19 17:36
 * @version: V1.0
 * @modified by:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MicroServiceServiceImpl extends BaseService implements MicroServiceService {
    @Resource
    private MicroServiceRepository microServiceRepository;

    /**
     * 分页查询部门列表
     *
     * @param param
     * @return
     */
    @Override
    public PageInfo<MicroServiceInfo> selectByConditionWithPage(MicroServiceParam param) {
        if (null != param.getPageNum() && null != param.getPageSize()) {
            PageHelper.startPage(param.getPageNum(), param.getPageSize());
        }

        List<MicroServiceInfo> microServiceInfos = microServiceRepository.selectByCondition(param);
        return new PageInfo<>(microServiceInfos);
    }

    /**
     * 添加部门信息
     *
     * @param microServiceInfo
     * @return
     */
    @Override
    public Boolean addMicroService(MicroServiceInfo microServiceInfo) {
        MicroServiceInfo param = new MicroServiceInfo();
        param.setServiceName(microServiceInfo.getServiceName());
        if (microServiceRepository.selectCount(param) > 0) {
            throw new DmcBizException(ErrorCodeEnum.UAC10012010);
        }

        microServiceInfo.setId(generateId());

        return microServiceRepository.save(microServiceInfo);
    }

    /**
     * 更新部门信息
     *
     * @param microServiceInfo
     * @return
     */
    @Override
    public Boolean updateMicroService(MicroServiceInfo microServiceInfo) {
        CheckUpdateDepartment(microServiceInfo);
        return microServiceRepository.updateByPrimaryKeySelective(microServiceInfo);
    }

    /**
     * 删除部门信息
     *
     * @param id
     * @return
     */
    @Override
    public Boolean deleteMicroService(Long id) {
        MicroServiceInfo microServiceInfo = new MicroServiceInfo();
        microServiceInfo.setId(id);
        CheckUpdateDepartment(microServiceInfo);

        return microServiceRepository.deleteByKey(id);
    }

    /**
     * 更新微服务状态
     *
     * @param microServiceInfo
     * @return
     */
    @Override
    public Boolean updateRoleStatusById(MicroServiceInfo microServiceInfo) {
        CheckUpdateDepartment(microServiceInfo);

        return microServiceRepository.update(microServiceInfo);
    }

    private void CheckUpdateDepartment(MicroServiceInfo microServiceInfo) {
        long deptId = microServiceInfo.getId();
        MicroServiceInfo param = new MicroServiceInfo();
        param.setId(deptId);
        int count = microServiceRepository.selectCount(param);
        if (count == 0) {
            throw new DmcBizException(ErrorCodeEnum.UAC10012011, deptId);
        }
    }
}
