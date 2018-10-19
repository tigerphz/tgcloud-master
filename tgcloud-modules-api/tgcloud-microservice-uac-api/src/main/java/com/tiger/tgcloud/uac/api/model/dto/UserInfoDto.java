package com.tiger.tgcloud.uac.api.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/19 11:24
 * @version: V1.0
 * @modified by:
 */
@Data
@ApiModel(value = "用户信息Dto")
public class UserInfoDto implements Serializable {

    private static final long serialVersionUID = 7181084368280104603L;

    /**
     * 登录名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 工号
     */
    private String code;

    /**
     * 手机号
     */
    private String mobile;

    private Integer isMobileActive;

    private String email;

    private Integer isEmailActive;

    /**
     * 用户来源
     */
    private String source;

    /**
     * 操作员类型（2000伙伴, 3000客户, 1000运营）
     */
    private String type;

    /**
     * 描述
     */
    private String qq;

    /**
     * 描述
     */
    private String remark;

    /**
     * 用户所属的组织ID
     */
    @ApiModelProperty(value = "用户所属的组织ID")
    private Long deptid;

    @ApiModelProperty(value = "用户所属的组织名称")
    private String deptName;
}
