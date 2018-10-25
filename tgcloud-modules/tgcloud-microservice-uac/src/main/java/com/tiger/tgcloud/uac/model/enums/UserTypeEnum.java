package com.tiger.tgcloud.uac.model.enums;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/25 12:29
 * @version: V1.0
 * @modified by:
 */

import java.util.Arrays;
import java.util.List;

/**
 * The enum Uac user type enum.
 *
 * @author paascloud.net@gmail.com
 */
public enum UserTypeEnum {

    /**
     * 运营
     */
    OPERATE("1000", "运营"),;

    /**
     * The Key.
     */
    String key;
    /**
     * The Value.
     */
    String value;

    UserTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
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
        for (UserTypeEnum ele : UserTypeEnum.values()) {
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
    public static UserTypeEnum getEnum(String key) {
        for (UserTypeEnum ele : UserTypeEnum.values()) {
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
    public static List<UserTypeEnum> getList() {
        return Arrays.asList(UserTypeEnum.values());
    }

}
