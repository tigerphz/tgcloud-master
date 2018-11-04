package com.tiger.tgcloud.uac.model.bo;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/29 16:22
 * @version: V1.0
 * @modified by:
 */
@Data
public class LoginedUserBO {
    private Long id;

    private String username;

    private String nickname;

    private List<String> roles;

    private List<String> permissions;

    private List<RouterTreeBO> routers;
}
