package com.tiger.tgcloud.dmc.repository;

import com.tiger.tgcloud.core.support.BaseRepository;
import com.tiger.tgcloud.dmc.api.model.domain.MicroServiceInfo;
import com.tiger.tgcloud.dmc.model.query.MicroServiceParam;
import com.tiger.tgcloud.dmc.repository.mapper.MicroServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/11/19 15:27
 * @version: V1.0
 * @modified by:
 */
@Repository
public class MicroServiceRepository extends BaseRepository<MicroServiceInfo> {
    @Autowired
    private MicroServiceMapper microServiceMapper;

    public List<MicroServiceInfo> selectByCondition(MicroServiceParam microServiceParam) {
        return microServiceMapper.selectByCondition(microServiceParam);
    }
}
