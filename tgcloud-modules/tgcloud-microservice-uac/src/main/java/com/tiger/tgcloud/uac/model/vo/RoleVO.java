package com.tiger.tgcloud.uac.model.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/24 17:53
 * @version: V1.0
 * @modified by:
 */
@Data
public class RoleVO {
    private Long id;

    @NotNull(message = "角色名不能为空")
    private String rolename;

    @NotNull(message = "角色名不能为空")
    private Integer status;

    @NotNull(message = "角色名不能为空")
    private String description;
}
