package com.tiger.tgcloud.dmc.service;

import com.tiger.tgcloud.dmc.api.model.domain.MqMessageData;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/20 22:13
 * @version: V1.0
 * @modified by:
 */
public interface MqMessageService {

    /**
     * 保存并发送消息日志
     *
     * @param mqMessageData
     * @return
     */
    Boolean saveAndSendMqMessage(MqMessageData mqMessageData);
}
