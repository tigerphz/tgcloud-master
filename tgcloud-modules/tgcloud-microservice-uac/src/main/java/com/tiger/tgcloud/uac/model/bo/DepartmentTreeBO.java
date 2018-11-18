package com.tiger.tgcloud.uac.model.bo;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/11/18 16:30
 * @version: V1.0
 * @modified by:
 */
@Data
public class DepartmentTreeBO {
    private Long id;

    private String deptname;

    private Integer status;

    private String description;

    private Long parentid;

    private Integer sort;

    private List<DepartmentTreeBO> children;
}
