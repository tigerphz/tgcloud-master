package com.tiger.tgcloud.uac.api.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/17 10:13
 * @version: V1.0
 * @modified by:
 */
@Data
@ApiModel(value = "用户注册Dto")
public class UserRegisterDto implements Serializable {

    private static final long serialVersionUID = -8019925037057525804L;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @NotNull(message = "用户名不能为空")
    private String userName;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @Length(min = 11, max = 11)
    private String mobileNo;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不能为空")
    @Size(min = 6)
    private String password;

    /**
     * 确认密码
     */
    @ApiModelProperty(value = "确认密码")
    @NotNull(message = "确认密码不能为空")
    @Size(min = 6)
    private String confirmPwd;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误")
    private String email;

    /**
     * 注册渠道
     */
    @ApiModelProperty(value = "注册渠道")
    @NotNull(message = "注册渠道不能为空")
    private String registerSource;
}
