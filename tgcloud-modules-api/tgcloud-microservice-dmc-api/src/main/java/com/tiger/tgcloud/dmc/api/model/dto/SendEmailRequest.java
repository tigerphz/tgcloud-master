package com.tiger.tgcloud.dmc.api.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @description:
 * @author: tiger
 * @date: 2018/10/20 20:46
 * @version: V1.0
 * @modified by:
 */
@Data
public class SendEmailRequest implements Serializable {

    private static final long serialVersionUID = -1097590414348293682L;

    private String text;
    private String subject;
    private Set<String> to;
}
