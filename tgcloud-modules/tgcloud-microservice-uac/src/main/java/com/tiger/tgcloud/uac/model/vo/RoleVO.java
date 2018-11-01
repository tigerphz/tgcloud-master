package com.tiger.tgcloud.uac.model.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

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

    /**
     * 版本号
     */
    private Integer version;
    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建人ID
     */
    private Long creatorId;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 最近操作人
     */
    private String updateOperator;

    /**
     * 最后操作人ID
     */
    private Long updateOperatorId;

    /**
     * 更新时间
     */
    private Date updateTime;
}
