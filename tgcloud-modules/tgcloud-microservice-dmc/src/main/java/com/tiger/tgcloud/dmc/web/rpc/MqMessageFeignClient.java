package com.tiger.tgcloud.dmc.web.rpc;

import com.tiger.tgcloud.core.support.BaseController;
import com.tiger.tgcloud.dmc.api.model.domain.MqMessageData;
import com.tiger.tgcloud.dmc.api.service.MqMessageFeignApi;
import com.tiger.tgcloud.dmc.service.MqMessageService;
import com.tiger.tgcloud.utils.wrapper.WrapMapper;
import com.tiger.tgcloud.utils.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/20 22:09
 * @version: V1.0
 * @modified by:
 */
@Slf4j
@RefreshScope
@RestController
@Api(value = "API - MqMessageFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MqMessageFeignClient extends BaseController implements MqMessageFeignApi {

    @Autowired
    private MqMessageService mqMessageService;

    /**
     * 保存并发送消息日志
     *
     * @param mqMessageData
     * @return
     */
    @Override
    @ApiOperation(httpMethod = "POST", value = "保存并发送消息日志")
    public Wrapper saveAndSendMqMessage(@RequestBody MqMessageData mqMessageData) {
        Boolean result = mqMessageService.saveAndSendMqMessage(mqMessageData);
        return WrapMapper.ok(result);
    }
}
