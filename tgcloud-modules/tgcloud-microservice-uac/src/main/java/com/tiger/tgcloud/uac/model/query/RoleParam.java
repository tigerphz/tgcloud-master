package com.tiger.tgcloud.uac.model.query;

import com.tiger.tgcloud.base.dto.BaseQueryCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/25 9:20
 * @version: V1.0
 * @modified by:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleParam extends BaseQueryCondition {
    private String rolename;

    private Integer status;

    private String description;

    private Long deptid;
}
