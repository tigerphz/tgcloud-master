package com.tiger.tgcloud.base.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/19 23:52
 * @version: V1.0
 * @modified by:
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MqTopicConstants {
    /**
     * The enum Uac mq topic enum.
     *
     * @author paascloud.net @gmail.com
     */
    public enum MqTopicEnum {
        /**
         * 发送短信.
         */
        SEND_SMS_TOPIC("SEND_SMS_TOPIC", "发送短信"),
        /**
         * 发送邮件.
         */
        SEND_EMAIL_TOPIC("SEND_EMAIL_TOPIC", "发送邮件");

        MqTopicEnum(String topic, String topicName) {
            this.topic = topic;
            this.topicName = topicName;
        }

        /**
         * The Topic.
         */
        String topic;
        /**
         * The Topic name.
         */
        String topicName;

        /**
         * Gets topic.
         *
         * @return the topic
         */
        public String getTopic() {
            return topic;
        }

    }


    /**
     * The enum Uac mq tag enum.
     *
     * @author paascloud.net @gmail.com
     */
    public enum MqTagEnum {

        /**
         * 注册获取验证码.
         */
        REGISTER_USER_AUTH_CODE("REGISTER_USER_AUTH_CODE", MqTopicEnum.SEND_SMS_TOPIC.getTopic(), "注册获取验证码"),
        /**
         * 修改密码获取验证码.
         */
        MODIFY_PASSWORD_AUTH_CODE("MODIFY_PASSWORD_AUTH_CODE", MqTopicEnum.SEND_SMS_TOPIC.getTopic(), "修改密码获取验证码"),
        /**
         * 忘记密码获取验证码.
         */
        FORGOT_PASSWORD_AUTH_CODE("FORGOT_PASSWORD_AUTH_CODE", MqTopicEnum.SEND_EMAIL_TOPIC.getTopic(), "忘记密码获取验证码"),

        /**
         * 激活用户.
         */
        ACTIVE_USER("ACTIVE_USER", MqTopicEnum.SEND_EMAIL_TOPIC.getTopic(), "激活用户"),
        /**
         * 激活用户成功.
         */
        ACTIVE_USER_SUCCESS("ACTIVE_USER_SUCCESS", MqTopicEnum.SEND_EMAIL_TOPIC.getTopic(), "激活用户成功"),
        /**
         * 重置密码
         */
        RESET_LOGIN_PWD("RESET_LOGIN_PWD", MqTopicEnum.SEND_EMAIL_TOPIC.getTopic(), "重置密码"),

        /**
         * 重置密码
         */
        RESET_USER_EMAIL("RESET_LOGIN_PWD", MqTopicEnum.SEND_EMAIL_TOPIC.getTopic(), "重置密码");
        /**
         * The Tag.
         */
        String tag;
        /**
         * The Topic.
         */
        String topic;
        /**
         * The Tag name.
         */
        String tagName;

        MqTagEnum(String tag, String topic, String tagName) {
            this.tag = tag;
            this.topic = topic;
            this.tagName = tagName;
        }

        /**
         * Gets tag.
         *
         * @return the tag
         */
        public String getTag() {
            return tag;
        }

        /**
         * Gets topic.
         *
         * @return the topic
         */
        public String getTopic() {
            return topic;
        }
    }

    /**
     * The class Topic obj.
     *
     * @author paascloud.net @gmail.com
     */
    static class TopicObj {

        private String topic;
        private Set<String> tagList;

        /**
         * Instantiates a new Topic obj.
         *
         * @param topic   the topic
         * @param tagList the tag list
         */
        TopicObj(String topic, Set<String> tagList) {
            this.topic = topic;
            this.tagList = tagList;
        }

        /**
         * Gets topic.
         *
         * @return the topic
         */
        String getTopic() {
            return topic;
        }

        /**
         * Gets tag list.
         *
         * @return the tag list
         */
        Set<String> getTagList() {
            return tagList;
        }
    }

    private static void trimEnd(StringBuilder stringBuilder, String suffix) {
        if (null == stringBuilder) {
            return;
        }
        String str = stringBuilder.toString();
        if (!StringUtils.isEmpty(suffix) && !str.endsWith(suffix)) {
            return;
        }
        stringBuilder.delete(str.length() - suffix.length(), str.length());
    }
}
