package com.tiger.tgcloud.uac.model.query;

import com.tiger.tgcloud.base.dto.BaseQueryCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/25 9:21
 * @version: V1.0
 * @modified by:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PermissionParam extends BaseQueryCondition {
    private String permname;

    private String url;

    private Integer status;

    private Integer type;
}
