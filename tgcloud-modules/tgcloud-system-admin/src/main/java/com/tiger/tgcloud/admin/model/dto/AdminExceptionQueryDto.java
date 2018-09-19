package com.tiger.tgcloud.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tiger.tgcloud.base.dto.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description:
 * @author: tiger
 * @date: 2018/9/19 11:47
 * @version: V1.0
 * @modified by:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class AdminExceptionQueryDto extends BaseQuery {

    private static final long serialVersionUID = 3967075132487249652L;
    /**
     * 操作用户名称
     */
    @ApiModelProperty(value = "操作用户名称")
    private String creator;
    /**
     * 异常原因
     */
    @ApiModelProperty(value = "异常原因")
    private String exceptionCause;

    /**
     * 异常栈信息
     */
    @ApiModelProperty(value = "异常栈信息")
    private String exceptionStack;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startQueryTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endQueryTime;
}
