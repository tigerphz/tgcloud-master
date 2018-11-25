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
@Table(name = "uac_role")
@Alias(value = "uacRole")
public class RoleInfo extends BaseEntity {
    private static final long serialVersionUID = -8618260859312718168L;

    private String rolename;

    private Integer status;

    private String description;
}
