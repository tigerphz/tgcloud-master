package com.tiger.tgcloud.dmc.api.model.domain;

import com.tiger.tgcloud.core.mybatis.BaseEntity;
import com.tiger.tgcloud.dmc.api.model.dto.MqMessageDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/20 17:05
 * @version: V1.0
 * @modified by:
 */
@Alias("dmcMqMessage")
@Table(name = "dmc_mq_message")
@Data
@NoArgsConstructor
public class MqMessageData extends BaseEntity {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 消息key
     */
    @Column(name = "message_key")
    private String messageKey;

    /**
     * topic
     */
    @Column(name = "message_topic")
    private String messageTopic;

    /**
     * tag
     */
    @Column(name = "message_tag")
    private String messageTag;

    /**
     * 消息内容
     */
    @Column(name = "message_body")
    private String messageBody;

    /**
     * 消息类型: 10 - 生产者 ; 20 - 消费者
     */
    @Column(name = "message_type")
    private Integer messageType;

    /**
     * 顺序类型, 0有序 1无序
     */
    private Integer orderType;

    /**
     * 消息状态
     */
    private Integer status;

    /**
     * 延时级别
     */
    @Column(name = "delay_level")
    private Integer delayLevel;

    /**
     * 是否删除 -0 未删除 -1 已删除
     */
    private Integer deleted;

    /**
     * producer group name
     */
    @Transient
    private String producerGroup;

    public MqMessageData(final String msgBody, final String topic, final String tag, final String key) {
        this.messageBody = msgBody;
        this.messageTopic = topic;
        this.messageTag = tag;
        this.messageKey = key;
    }

    /**
     * Gets tpc mq message dto.
     *
     * @return the tpc mq message dto
     */
    @Transient
    public MqMessageDto getMqMessageDto() {
        MqMessageDto mqMessageDto = new MqMessageDto();
        mqMessageDto.setMessageBody(this.messageBody);
        mqMessageDto.setMessageKey(this.messageKey);
        mqMessageDto.setMessageTag(this.messageTag);
        mqMessageDto.setMessageTopic(this.messageTopic);
        mqMessageDto.setMessageType(this.messageType);
        mqMessageDto.setRefNo(this.messageKey);
        mqMessageDto.setDelayLevel(this.delayLevel);
        mqMessageDto.setOrderType(this.orderType);
        mqMessageDto.setProducerGroup(this.producerGroup);
        return mqMessageDto;
    }
}
