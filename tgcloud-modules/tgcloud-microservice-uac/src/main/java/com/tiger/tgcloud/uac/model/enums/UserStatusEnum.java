package com.tiger.tgcloud.uac.model.enums;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/17 12:06
 * @version: V1.0
 * @modified by:
 */
public enum UserStatusEnum {

    /*
     * 删除
     */
    Delete(-1, "未启用"),
    /*
     * 启用
     */
    ENABLE(0, "启用"),
    /**
     * 禁用
     */
    DISABLE(1, "禁用");

    /**
     * The Key.
     */
    Integer key;
    /**
     * The Value.
     */
    String value;

    UserStatusEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public Integer getKey() {
        return key;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * 获取key获取value
     *
     * @param key key
     * @return value value
     */
    public static String getValue(String key) {
        for (UserStatusEnum ele : UserStatusEnum.values()) {
            if (key.equals(ele.getKey())) {
                return ele.getValue();
            }
        }
        return null;
    }

    /**
     * 根据key获取该对象
     *
     * @param key key
     * @return this enum
     */
    public static UserStatusEnum getEnum(String key) {
        for (UserStatusEnum ele : UserStatusEnum.values()) {
            if (key.equals(ele.getKey())) {
                return ele;
            }
        }
        return null;
    }

    /**
     * 获取List集合
     *
     * @return List list
     */
    public static List<UserStatusEnum> getList() {
        return Arrays.asList(UserStatusEnum.values());
    }
}
