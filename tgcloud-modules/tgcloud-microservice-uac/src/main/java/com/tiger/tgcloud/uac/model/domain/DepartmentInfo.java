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
@Table(name = "uac_department")
@Alias(value = "uacDepartment")
public class DepartmentInfo extends BaseEntity {
    private static final long serialVersionUID = -355243749586445864L;

    private String deptname;

    private Integer status;

    private String description;

    private Integer sort;

    private Long parentid;
}
