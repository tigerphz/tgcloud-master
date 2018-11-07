package com.tiger.tgcloud.uac.model.bo;

import lombok.Data;

import java.util.List;
import java.util.Map;

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

    private Map<String, String> props;

    private RouterMetaBO meta;

    private String redirect;

    private List<RouterTreeBO> children;
}
