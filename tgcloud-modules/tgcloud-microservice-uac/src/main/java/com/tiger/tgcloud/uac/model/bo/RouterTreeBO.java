package com.tiger.tgcloud.uac.model.bo;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/11/3 23:58
 * @version: V1.0
 * @modified by:
 */
@Data
public class RouterTreeBO {

    private String path;

    private String name;

    private String component;

    private RouterMetaBO meta;

    private String redirect;

    private List<RouterTreeBO> children;
}
