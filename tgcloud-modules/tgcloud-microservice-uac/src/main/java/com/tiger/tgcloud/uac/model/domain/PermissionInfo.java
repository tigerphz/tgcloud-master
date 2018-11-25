package com.tiger.tgcloud.uac.model.domain;

import com.tiger.tgcloud.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import javax.persistence.Table;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/24 16:21
 * @version: V1.0
 * @modified by:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "uac_permission")
@Alias(value = "uacPermission")
public class PermissionInfo extends BaseEntity {
    private static final long serialVersionUID = 8604906853225815789L;

    private String permname;

    private Integer status;

    private String description;

    private Integer type;

    private String icon;

    private Integer sort;

    private Long parentid;

    private String url;

    private String action;

    /**
     * 权限标识
     */
    private String code;

    /**
     * 导航组件
     */
    private String component;

    /**
     * 导航标题
     */
    private String title;

    /**
     * 导航路径
     */
    private String path;

    /**
     * 导航名称
     */
    private String name;

    /**
     * 是否导航
     */
    private Boolean isnavigate;

    /**
     * 是否插件
     */
    private Boolean isplugin;
}
