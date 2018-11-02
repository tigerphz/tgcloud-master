package com.tiger.tgcloud.uac.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/11/2 21:52
 * @version: V1.0
 * @modified by:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRolesVO {
    @NonNull
    private Long userId;

    private List<Long> roleIds;
}
