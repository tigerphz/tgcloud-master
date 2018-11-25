package com.tiger.tgcloud.uac.model.bo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/26 17:07
 * @version: V1.0
 * @modified by:
 */
@Data
public class MenuBO {
    private Long id;

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

    /**
     * 导航名称
     */
    private String name;

    private Boolean isnavigate;

    private Boolean isplugin;

    private Date createdate;

    private String createusername;

    private List<MenuBO> children;
}
