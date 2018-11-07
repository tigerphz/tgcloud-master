package com.tiger.tgcloud.uac.model.domain;

import com.tiger.tgcloud.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
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

    private String code;

    private String component;

    private String title;

    private String path;

    private Boolean isnavigate;

    private Boolean isplugin;
}
