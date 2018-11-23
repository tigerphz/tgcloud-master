package com.tiger.tgcloud.uac.api.model.domain;

import com.tiger.tgcloud.core.mybatis.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/17 10:17
 * @version: V1.0
 * @modified by:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "uac_user")
@Alias(value = "uacUser")
public class UserInfo extends BaseEntity {
    private static final long serialVersionUID = 5465775492730080699L;

    /**
     * 登录名
     */
    @Column(name = "username")
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 登录密码
     */
    @Column(name = "passwordhash")
    private String passwordhash;

    /**
     * 盐,用于shiro加密, 字段停用
     */
    @Column(name = "passwordsalt")
    private String passwordsalt;

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
    @Column(name = "mobile")
    private String mobile;

    @Column(name = "is_mobile_active")
    private Integer isMobileActive;

    private String identitycard;

    private String email;

    @Column(name = "is_email_active")
    private Integer isEmailActive;

    /**
     * 用户来源
     */
    @Column(name = "source")
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
    private Long deptid;

    @ApiModelProperty(value = "用户所属的组织名称")
    @Transient
    private String deptname;
}
