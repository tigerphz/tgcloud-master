package com.tiger.tgcloud.dmc.api.service;

import com.tiger.tgcloud.dmc.api.model.domain.MqMessageData;
import com.tiger.tgcloud.dmc.api.service.hystrix.MqMessageFeignHystrix;
import com.tiger.tgcloud.security.feign.OAuth2FeignAutoConfiguration;
import com.tiger.tgcloud.utils.wrapper.Wrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/20 21:58
 * @version: V1.0
 * @modified by:
 */
@FeignClient(value = "tgcloud-microservice-dmc", configuration = OAuth2FeignAutoConfiguration.class, fallback = MqMessageFeignHystrix.class)
public interface MqMessageFeignApi {

    /**
     * @param mqMessageData
     * @return
     */
    @PostMapping(value = "/api/mq/saveAndSendMqMessage")
    Wrapper saveAndSendMqMessage(@RequestBody MqMessageData mqMessageData);

}
