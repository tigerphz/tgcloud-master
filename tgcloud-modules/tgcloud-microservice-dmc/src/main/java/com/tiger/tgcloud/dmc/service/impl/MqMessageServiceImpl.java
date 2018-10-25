package com.tiger.tgcloud.dmc.service.impl;

import com.tiger.tgcloud.core.support.BaseService;
import com.tiger.tgcloud.dmc.api.model.domain.MqMessageData;
import com.tiger.tgcloud.dmc.repository.mapper.MqMessageMapper;
import com.tiger.tgcloud.dmc.service.MqMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/20 22:13
 * @version: V1.0
 * @modified by:
 */
@Service
public class MqMessageServiceImpl extends BaseService implements MqMessageService {
    @Autowired
    private MqMessageMapper mqMessageMapper;

    /**
     * 保存并发送消息日志
     *
     * @param mqMessageData
     * @return
     */
    @Override
    public Boolean saveAndSendMqMessage(MqMessageData mqMessageData) {
        return mqMessageMapper.insertSelective(mqMessageData) > 0;
    }
}
