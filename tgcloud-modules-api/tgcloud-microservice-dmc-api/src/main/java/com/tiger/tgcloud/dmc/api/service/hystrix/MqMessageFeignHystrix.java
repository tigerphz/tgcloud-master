package com.tiger.tgcloud.dmc.api.service.hystrix;

import com.tiger.tgcloud.dmc.api.model.domain.MqMessageData;
import com.tiger.tgcloud.dmc.api.service.MqMessageFeignApi;
import com.tiger.tgcloud.utils.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/20 21:59
 * @version: V1.0
 * @modified by:
 */
@Component
@Slf4j
public class MqMessageFeignHystrix implements MqMessageFeignApi {
    /**
     * @param mqMessageData
     * @return
     */
    @Override
    public Wrapper saveAndSendMqMessage(MqMessageData mqMessageData) {
        log.error("feign client MqMessageFeignApi.saveAndSendMqMessage() fail");
        return null;
    }
}
