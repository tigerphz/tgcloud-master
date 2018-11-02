package com.tiger.tgcloud.uac.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

/**
 * @description:
 * @author: tiger
 * @date: 2018/11/3 0:02
 * @version: V1.0
 * @modified by:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermsVO {
    @NonNull
    private Long roleId;

    private List<Long> permIds;
}
