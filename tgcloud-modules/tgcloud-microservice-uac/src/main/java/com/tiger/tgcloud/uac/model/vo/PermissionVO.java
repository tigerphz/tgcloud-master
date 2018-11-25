package com.tiger.tgcloud.uac.model.vo;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/24 17:54
 * @version: V1.0
 * @modified by:
 */
@Data
public class PermissionVO {
    private Long id;

    @NotNull(message = "角色名不能为空")
    private String permname;

    private Integer status;

    @NotNull(message = "描述不能为空")
    private String description;

    @NotNull(message = "类型不能为空")
    private Integer type;

    private String icon;

    @DecimalMin(value = "0", message = "类型不能为空")
    private Integer sort;

    private Long parentid;

    private String url;

    private String action;

    private String code;

    private String component;

    private String title;

    private String path;

    /**
     * 导航名称
     */
    private String name;

    private Boolean isnavigate;

    private Boolean isplugin;
}
