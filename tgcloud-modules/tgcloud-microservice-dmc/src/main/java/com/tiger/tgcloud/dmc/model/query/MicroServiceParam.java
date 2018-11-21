package com.tiger.tgcloud.dmc.model.query;

import com.tiger.tgcloud.base.dto.BaseQueryCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: tiger
 * @date: 2018/11/19 18:01
 * @version: V1.0
 * @modified by:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class MicroServiceParam extends BaseQueryCondition {

    private static final long serialVersionUID = -1367254386871512946L;

    /**
     * 服务名称
     */
    @ApiModelProperty(value = "服务名称")
    public String serviceName;

    @ApiModelProperty(value = "服务状态")
    public Integer status;
}
