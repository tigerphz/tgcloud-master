package com.tiger.tgcloud.uac.model.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/24 17:54
 * @version: V1.0
 * @modified by:
 */
@Data
public class DepartmentVO {
    private Long id;

    @NotNull(message = "部门名不能为空")
    private String deptname;

    private Integer status;

    private String description;

    private Long parentid;

    private Date createdate;

    private String createusername;

    private Date modifydate;

    private String modifyusername;
}
