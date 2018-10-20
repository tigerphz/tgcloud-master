package com.tiger.tgcloud.uac.mq;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.tiger.tgcloud.base.constant.MqTopicConstants;
import com.tiger.tgcloud.base.enums.ErrorCodeEnum;
import com.tiger.tgcloud.dmc.api.model.domain.MqMessageData;
import com.tiger.tgcloud.dmc.api.model.dto.SendEmailRequest;
import com.tiger.tgcloud.uac.api.exceptions.UacBizException;
import com.tiger.tgcloud.uac.model.enums.EmailTemplateEnum;
import com.tiger.tgcloud.uac.service.FreeMarkerService;
import com.tiger.tgcloud.utils.RedisKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/19 23:59
 * @version: V1.0
 * @modified by:
 */
@Component
@Slf4j
public class EmailProducer {
    @Resource
    private FreeMarkerService freeMarkerService;

    /**
     * Send email mq.
     *
     * @param emailSet          the email set
     * @param emailTemplateEnum the email template enum
     * @param param             the param
     */
    public MqMessageData sendEmailMq(Set<String> emailSet, EmailTemplateEnum emailTemplateEnum, MqTopicConstants.MqTagEnum tagEnum, Map<String, Object> param) {
        log.info("pcSendEmailRequest - 发送邮件MQ. emailSet={}, param={}", emailSet, param);
        String msgBody;
        try {

            Preconditions.checkArgument(emailTemplateEnum != null, "邮箱模板信息不存在");
            SendEmailRequest request = new SendEmailRequest();

            String templateLocation = emailTemplateEnum.getLocation();
            String text = freeMarkerService.getTemplate(param, templateLocation);

            request.setText(text);
            request.setTo(emailSet);
            request.setSubject(emailTemplateEnum.getSubject());

            msgBody = JSON.toJSONString(request);
        } catch (Exception e) {
            log.error("发送邮件验证码 smsMessage转换为json字符串失败", e);
            throw new UacBizException(ErrorCodeEnum.UAC10011021);
        }
        String topic = tagEnum.getTopic();
        String tag = tagEnum.getTag();
        String key = RedisKeyUtil.createMqKey(topic, tag, emailSet.toString(), msgBody);
        return new MqMessageData(msgBody, topic, tag, key);
    }
}
