package com.tiger.tgcloud.uac.model.query;

import com.tiger.tgcloud.base.dto.BaseQueryCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/24 18:17
 * @version: V1.0
 * @modified by:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserParam extends BaseQueryCondition {
    private String username;

    private String nickname;

    private Integer gender;

    private Integer status;

    private String mobile;

    private String identitycard;

    private String email;

    private String qq;

    private String code;

    private String type;

    private String source;

    private String address;

    private Long deptid;
}